---
title: Space-Invaders
codebraid:
  jupyter: true
---
 





```{.java .cb-run}
import static java.lang.IO.println;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public record V2(int x, int y) {

    V2 plus(V2 other) {
        return new V2(x + other.x(), y + other.y());
    }

}

public record StringWithLocation(String string, V2 location) {
}

public interface IBasicGameObject {
    V2 pos();
    List<StringWithLocation> show();
    List<V2> hitBox();
    boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height);
}



public interface Rocket extends IBasicGameObject {
    Rocket move();
    boolean isPlayerRocket();
}

public interface Shooting {
    Rocket shoot();
}

```
```{.java .cb-run}


public class Utils {

    public static <T> boolean intersect(List<T> xs, List<T> ys) {
        for (var x : xs) {
            if (ys.contains(x)) {
                return true;
            }
        }
        return false;
    }

    public static V2 charToV2(char dir) {
        return switch (dir) {
            case 'a' -> new V2(-1, 0);
            case 'd' -> new V2(1, 0);
            default -> new V2(0, 0);
        };
    }

    public static boolean isOnBoard(V2 v, int width, int height) {
        return v.x() >= 0 && v.x() < width && v.y() >= 0 && v.y() < height;
    }

    /**
     * Returns the x-coordinates of all points in the given list.
     * Useful for expressing "some x == value" as "xCoordinates.contains(value)".
     */
    public static List<Integer> getXCoordinates( List<V2>  xs) {
        var acc = new ArrayList<Integer>();
        for (var v : xs) {
            acc.add(v.x());
        }
        return acc;
    }

    public static List<Integer> getYCoordinates( List<V2>  xs) {
        var acc = new ArrayList<Integer>();
        for (var v : xs) {
            acc.add(v.y());
        }
        return acc;
    }

    public static boolean isOnBoard(List<V2> xs, int width, int height) {
        for (var v : xs) {
            if (isOnBoard(v, width, height)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T random(List<T> xs) {
        if (xs.isEmpty()) return null;
        var random = new Random();
        var index = random.nextInt(xs.size());
        return xs.get(index);
    }

    public static String repeat(String s, int count){
        var acc = "";
        for (int i =0; i < count; i++){
            acc += s;
        }
        return acc;
    }
    

    public static List<StringWithLocation> getStringsWithLocation(List<IBasicGameObject> basicGameObjects) {
        var acc = new ArrayList<StringWithLocation>();
        for (var bgo : basicGameObjects) {
            acc.addAll(bgo.show());
        }
        return acc;
    }

    static <T extends IBasicGameObject> List<T> removeDeadObjects(List<T> gameObjectsToFilter, List<IBasicGameObject> allGameObjects, int width, int height) {
        var acc = new ArrayList<T>();
        for (var go : gameObjectsToFilter) {
            if (go.isAlive(allGameObjects, width, height)) {
                acc.add(go);
            }
        }
        return acc;
    }


    static boolean containsNoPlayerRocket(List<Rocket> rockets) {
        for (var rocket : rockets) {
            if (rocket.isPlayerRocket()) {
                return false;
            }
        }
        return true;
    }


    public static List<Rocket> move(List<Rocket> rockets) {
        var res = new ArrayList<Rocket>();
        for (var rocket : rockets) {
            res.add(rocket.move());
        }
        return res;
    }



}


```
```{.java .cb-run}



public record BasicGameObject(V2 pos, String displayString) implements IBasicGameObject {



    @Override
    public  List<StringWithLocation> show(){
        var lines = displayString.lines().toList();
        var acc = new ArrayList<StringWithLocation>();
        for (int rowIndex = 0; rowIndex < lines.size(); rowIndex++) {
            acc.add(new StringWithLocation(lines.get(rowIndex), pos.plus(new V2(0, rowIndex))));
        }
        return acc;
    }

    @Override
    public  List<V2> hitBox() {
        var acc = new ArrayList<V2>();
        for (var stringWithLocation : show()) {
            for (int colIndex = 0; colIndex < stringWithLocation.string().length(); colIndex++) {
                acc.add(stringWithLocation.location().plus(new V2(colIndex, 0)));
            }
        }
        return acc;
    }




    public boolean checkCollision(IBasicGameObject other){
        return Utils.intersect(hitBox(),other.hitBox());
    }

    // returns true if the object collides with another object in the list.
    // We expect "all" to contain this object itself, so we check "collisionCount > 1".
    public boolean checkCollision(List<IBasicGameObject>  all){
        var collisionCount = 0;
        for (var other : all) {
            if (checkCollision(other)) {
                collisionCount++;
            }
        }
        return collisionCount > 1;
    }


    @Override
    public boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height) {
        return Utils.isOnBoard(hitBox(), width, height) && !(checkCollision(gameObjects));
    }

}

public record MovableGameObject(BasicGameObject basicGameObject) implements IBasicGameObject{

    public MovableGameObject(V2 pos, String displayString) {
        this(new BasicGameObject(pos,displayString));
    }

    @Override
    public V2 pos() {
        return basicGameObject.pos();
    }

    public MovableGameObject move(V2 dir){
        return new MovableGameObject(pos().plus(dir), basicGameObject.displayString());
    }



    @Override
    public List<StringWithLocation> show() {
        return basicGameObject().show();
    }

    @Override
    public List<V2> hitBox() {
        return basicGameObject.hitBox();
    }

    @Override
    public boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height) {
        return basicGameObject.isAlive(gameObjects, width, height);
    }


    boolean touchesLeftBorder() {
        // hitbox touches x == 0
        return Utils.getXCoordinates(hitBox()).contains(0);
    }

    boolean touchesRightBorder(int width) {
        // hitbox touches the rightmost valid x coordinate
        return Utils.getXCoordinates(hitBox()).contains(width -1); }
}



public record AlienRocket(MovableGameObject mgo) implements IBasicGameObject, Rocket {

    AlienRocket(V2 pos){
        this(new MovableGameObject(pos,"|\nˇ"));
    }

    @Override
    public V2 pos() {
        return mgo.pos();
    }

    @Override
    public List<StringWithLocation> show() {
        return mgo.show();
    }

    @Override
    public List<V2> hitBox() {
        return mgo.hitBox();
    }



    @Override
    public boolean isPlayerRocket() {
        return false;
    }

    @Override
    public AlienRocket move() {
        return new AlienRocket(mgo.move(new V2(0,1)));
    }

    @Override
    public boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height) {
        return mgo.isAlive(gameObjects, width, height);
    }


}

public interface Shooting {
    Rocket shoot();
}


public record Alien(MovableGameObject mgo) implements IBasicGameObject, Shooting {


    Alien(V2 pos, String displayString){
        this(new MovableGameObject(pos, displayString));
    }

    public Alien move(V2 dir) {
        return new Alien(mgo.move(dir));
    }


    @Override
    public List<StringWithLocation> show() {
        return mgo.show();
    }


    @Override
    public List<V2> hitBox() {
        return mgo.hitBox();
    }

    @Override
    public V2 pos() {
        return mgo.pos();
    }
    @Override
    public AlienRocket shoot() {
        return new AlienRocket(pos().plus(new V2(0,2)));
    }




    @Override
    public boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height) {
        return mgo.isAlive(gameObjects, width, height);
    }


    boolean touchesLeftBorder() {
        return mgo.touchesLeftBorder();
    }

    boolean touchesRightBorder(int width) {
        return mgo.touchesRightBorder(width);
    }

    /**
     * Returns true if any part of this alien's hitbox reaches the last playable line.
     *
     * We define the last playable line as y == height - vertical sie of the alien.
     */

    boolean isInLastLine(int height) {
        int lastPossibleLine = height - 1;
        return Utils.getYCoordinates(hitBox()).contains(lastPossibleLine);
    }


}


public record PlayerRocket(MovableGameObject mgo) implements IBasicGameObject, Rocket {

    PlayerRocket(V2 pos){
        this(new MovableGameObject(pos,"|\n^"));
    }

    @Override
    public V2 pos() {
        return mgo.pos();
    }

    @Override
    public List<StringWithLocation> show() {
        return mgo.show();
    }

    @Override
    public List<V2> hitBox() {
        return mgo.hitBox();
    }

    @Override
    public Rocket move() {
        return new PlayerRocket(mgo.move(new V2(0,-1)));
    }

    @Override
    public boolean isPlayerRocket() {
        return true;
    }

    @Override
    public boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height) {
        return mgo.isAlive(gameObjects, width, height);
    }
}


public record Player(MovableGameObject mgo) implements IBasicGameObject, Shooting {

    Player(V2 pos){
        this(new MovableGameObject(pos, "_/MM\\_\nqWAAWp"));
    }

    @Override
    public V2 pos() {
        return mgo.pos();
    }

    @Override
    public List<StringWithLocation> show() {
        return mgo.show();
    }


    @Override
    public List<V2> hitBox() {
        return mgo.hitBox();
    }

    public Player move(V2 dir) {
        return new Player(mgo().move(dir));
    }

    Player reactToBorder(int width) {
       if (mgo.touchesRightBorder(width))
           return  move(new V2(-1, 0));
       else if (mgo.touchesLeftBorder())
           return move(new V2(1, 0));
       else
           return this;
    }

    public Player moveBounded(V2 dir, int width) {
        return move(dir).reactToBorder(width);
    }

    public PlayerRocket shoot() {
        return new PlayerRocket(pos().plus(new V2(0,-2)));
    }



    @Override
    public boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height) {
        return mgo.isAlive(gameObjects, width, height);
    }

}

public record Plasma(MovableGameObject mgo) implements IBasicGameObject, Rocket {


    Plasma(V2 pos, int height){


        String rocket =  Utils.repeat("(((||||||||||)))\n", height);


        var mgo = new MovableGameObject(pos,rocket); // "/|\\\n|||\n|||\n|||\n|||"));
        this(mgo);
    }
    @Override
    public V2 pos() {
        return mgo.pos();
    }

    @Override
    public Plasma move() {
        return new Plasma((mgo.move(new V2(0,-3))));
    }

    @Override
    public boolean isPlayerRocket() {
        return true;
    }



    @Override
    public List<StringWithLocation> show() {
        return mgo.show();
    }

    @Override
    public List<V2> hitBox() {
        return mgo.hitBox();
    }


    // the super rocket is always alive. it flyes to the edge of the board.
    @Override
    public boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height) {
        return Utils.isOnBoard(hitBox(), width, height);
    }
}
```

```{.java .cb-run line_numbers=false}
public record CountDown(int start,
    int current){

    public CountDown(int start) {
        this(start, start);
    }


    public CountDown countDown(){
        if (current > 0) {
            return new CountDown(start, current -1);
        }
        else {
            return new CountDown(start);
        }

    }

    public boolean finished(){
        return this.current == 0;
    }
}

```

```{.java .cb-run line_numbers=false}

public record AlienSwarm(
V2 aliensDirection,
List<Alien> aliens,
CountDown alienRocketCountdown
) {


    static String rowToAlienStrings(int i){
        return switch (i){
            case 2 -> "{@@}\n/\"\"\\" ;
            case 1 -> "/MM\\\n\\~~/";
            case 0 -> "{OO}\n/VV\\";
            default -> throw new IllegalStateException("Unexpected value: " + i);
        };

    }

    public static  List<Alien>createAliens (){
        var res = new ArrayList<Alien>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 10; col++) {
                var x = 36 - col* 4;
                var y = 8 - row * 4;
                var pos = new V2(x, y);
                res.add(new Alien(pos, rowToAlienStrings(row)));

            }
        }return res;
    }

    AlienSwarm() {
        this(new V2(1, 0), createAliens(), new CountDown(5));
    }

    public boolean noAliensLeft() {
        return aliens.isEmpty();
    }


    public boolean aliensAreInLastLine(int height) {
        for (var alien : aliens) {
            if (alien.isInLastLine(height)) {
                return true;
            }
        }
        return false;
    }


    public AlienSwarm move(){
        var res = new ArrayList<Alien>();
        for (var mgo : aliens) {
            res.add(mgo.move(aliensDirection));
        }
        return new AlienSwarm(aliensDirection, res, alienRocketCountdown.countDown());
    }

    public boolean touchesLeftBorder() {
        for (var alien : aliens) {
            if (alien.touchesLeftBorder()) {
                return true;
            }
        }
        return false;
    }

    public boolean touchesRightBorder(int width) {
        for (var alien : aliens) {
            if (alien.touchesRightBorder(width)) {
                return true;
            }
        }
        return false;
    }


        V2 computeNextAlienDirection(int width) {
            boolean movingRight = aliensDirection.equals(new V2(1, 0));
            boolean movingLeft  = aliensDirection.equals(new V2(-1, 0));
            boolean movingDown  = aliensDirection.equals(new V2(0, 1));

            boolean atRightBorder = touchesRightBorder(width);
            boolean atLeftBorder  = touchesLeftBorder();

            // When moving horizontally and touching a side, start moving down
            if ((movingRight && atRightBorder) || (movingLeft && atLeftBorder)) {
                return new V2(0, 1);
            }

            // When moving down on the right side, start moving left
            if (movingDown && atRightBorder) {
                return new V2(-1, 0);
            }

            // When moving down on the left side, start moving right
            if (movingDown && atLeftBorder) {
                return new V2(1, 0);
            }

            return aliensDirection;
        }

    public AlienSwarm reactToBorder(int width){
        return new AlienSwarm(computeNextAlienDirection(width), aliens,alienRocketCountdown);
    }

    public AlienSwarm moveBounded(int width) {
        return move().reactToBorder(width);
    }

    public boolean countDownFinished() {
        return alienRocketCountdown.finished();
    }

    /**
     * Returns all aliens in the lowest row.
     * <p>
     * Invariant: aliens are ordered so that all aliens in the lowest row
     * come first in the list. Therefore, we can look only at the first element's y.
     */
    List<Alien> getLowestAliens() {
        if (aliens.isEmpty()) return List.of();
        int lowestLine = aliens.getFirst().pos().y();
        var acc = new ArrayList<Alien>();
        for (var alien : aliens) {
            if (alien.pos().y() == lowestLine) {
                acc.add(alien);
            }
        }
        return acc;
    }

    public Alien getShootingAlien() {
        if (countDownFinished()){
            return Utils.random(getLowestAliens());}
        return null;
    }

    public AlienSwarm removeDeadAliens(List<IBasicGameObject> allGameObjects, int width, int height) {
        var newAliens = Utils.removeDeadObjects(aliens, allGameObjects, width, height);
        return new AlienSwarm(aliensDirection, newAliens, alienRocketCountdown);
    }
}
```

```{.java .cb-run line_numbers=false}
public record InvisiblePlasmaCannon(V2 pos) implements Shooting{

    @Override
    public Rocket shoot() {
        var plasmaHeight =  pos.y();
        return new Plasma(  pos().plus(new V2(0,-plasmaHeight)), plasmaHeight);
    }
}
```


```{.java .cb-run line_numbers=false}
public class RocketFactory {
    
    public static List<Shooting> getShootingObjects(List<Rocket> rockets, Alien shootingAlien, Player player, char pressedKey) {
        var shootingObjects = new ArrayList<Shooting>();
        if (shootingAlien != null) {
            shootingObjects.add(shootingAlien);
        }
        var playerCanShoot = Utils.containsNoPlayerRocket(rockets);
        if (pressedKey == 'k' && playerCanShoot) {
            shootingObjects.add(player);
        }
        if (pressedKey == 'l' && playerCanShoot) {
            shootingObjects.add(new InvisiblePlasmaCannon(player.pos()));
        }
        return shootingObjects;
    }

    public static List<Rocket> getNewRockets(List<Shooting> shootingObjects) {
        var acc = new ArrayList<Rocket>();
        for (var shootingObject : shootingObjects) {
            acc.add(shootingObject.shoot());
        }
        return acc;
    }

    public static List<Rocket> getNewRockets(List<Rocket> rockets, Alien shootingAlien, Player player, char key) {
        var shootingObjects = getShootingObjects(rockets, shootingAlien, player, key);
        return getNewRockets(shootingObjects);

    }
        public static List<Rocket> addNewRockets(List<Rocket> rockets, Alien shootingAlien, Player player, char key) {
        var currentRockets = new ArrayList<Rocket>(rockets);
        var newRockets = getNewRockets(rockets, shootingAlien, player, key);
        currentRockets.addAll(newRockets);
        return currentRockets;

    }


}
```




```{.java .cb-run line_numbers=false}
public class LevelFactory {
public static List<BasicGameObject> generateBlock(V2 pos, int cols, int rows) {
var acc = new ArrayList<BasicGameObject>();
for (int y = 0; y < rows; y++) {
for (int x = 0; x < cols; x++) {
acc.add(new BasicGameObject(pos.plus(new V2(x, y)), "#"));
}
}
return acc;
}

    public static List<BasicGameObject> generateBlocks(V2 startPos, int cols, int rows, int count) {
        var acc = new ArrayList<BasicGameObject>();
        var pos = startPos;
        for (int i = 0; i < count; i++) {

            acc.addAll(generateBlock(pos, cols, rows));
            pos = pos.plus(new V2(cols + 2, 0));
        }
        return acc;
    }


    public static List<BasicGameObject> generateBlocks(int width, int height) {
        int blockCols = 4;
        int blockGap = 2;
        int blockRows = 3;
        int blockCount = width / (blockCols + blockGap);
        return generateBlocks(new V2(1, 3 * height / 4), blockCols, blockRows, blockCount);
    }


}
```


```{.java .cb-run line_numbers=false}
public class Model {
    public final int width;
    public final int height;
    Player player;
    AlienSwarm alienSwarm;
    List<BasicGameObject> blocks;
    public List<Rocket> rockets;

    void restart() {
        this.player = new Player(new V2(width /2, height -2));
        this.alienSwarm = new AlienSwarm();
        this.blocks = LevelFactory.generateBlocks(width, height );
        this.rockets = new ArrayList<>();
    }

    public Model(int width, int height) {
        this.width = width;
        this.height = height;
        restart();
    }

    public Model(int width, int height, V2 playerPos, List<Alien> alienSwarm, List<BasicGameObject> blocks) {
        this.width = width;
        this.height = height;
        this.player =new Player(playerPos);
        this.alienSwarm = new AlienSwarm(new V2(1,0), alienSwarm, new CountDown(5));
        this.blocks = blocks;
        this.rockets = List.of();
    }


    public Model(int width, int height, V2 playerPos, List<Alien> alienSwarm, List<BasicGameObject> blocks, List<Rocket> rockets) {
        this.width = width;
        this.height = height;
        this.player =new Player(playerPos);
        this.alienSwarm = new AlienSwarm(new V2(1,0), alienSwarm, new CountDown(5));
        this.blocks = blocks;
        this.rockets =(rockets);
    }


    boolean gameWon(){
        return alienSwarm.noAliensLeft();
    }

    public String getEndMessage(){
        if (gameWon()){
            return "You won!";
        }
        return "You lost!";
    }

    void move(char dir){

        alienSwarm = alienSwarm.moveBounded(width);
        player = player.moveBounded(Utils.charToV2(dir),width);
        rockets = (Utils.move(rockets));
    }


    public List<IBasicGameObject> gameObjects(){
        var acc = new ArrayList<IBasicGameObject>();
        acc.addAll(blocks);
        acc.addAll(alienSwarm.aliens());
        acc.addAll(rockets);
        acc.add(player);
        return acc;
    }


    boolean playerIsAlive() {
        return player.isAlive(gameObjects(),width,height);
    }

    boolean gameLost(){
        return  alienSwarm.aliensAreInLastLine(height) || !playerIsAlive();
    }


    public boolean gameOngoing() {
        return !gameWon() && !gameLost();
    }









    public List<StringWithLocation>  getUIState(){
        return Utils.getStringsWithLocation(gameObjects());
    }




    public void removeDeadObjects(){
        List<IBasicGameObject> allGameObjects = gameObjects();
        blocks = Utils.removeDeadObjects(blocks, allGameObjects, width,height);
        alienSwarm = alienSwarm.removeDeadAliens(allGameObjects,width, height);
        rockets = (Utils.removeDeadObjects(rockets, allGameObjects, width,height));
    }

    public void update(char key){
        removeDeadObjects();
        move(key);
        rockets = RocketFactory.addNewRockets(rockets, alienSwarm.getShootingAlien(), player, key);
    }

}
```





Definiere jedes Interface in einer eigenen `.java`-Datei, die den Namen des Interfaces trägt, z. B. `IBasicGameObject.java` für `IBasicGameObject`.


#  IBasicGameObject

Aufgabe: Lege das Interface `IBasicGameObject` an und deklare die Methoden

- `List<StringWithLocation> show()`
- `List<V2> hitBox()`
- `boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height)`


# BasicGameObject

## BasicGameObject — isAlive

Aufgabe: Ergänze `boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height)` in `BasicGameObject`.
Die Methode soll immer `true` zurückgeben.

## Aufgabe
Ergänze in der Klasse `BasicGameObject` die Interface-Deklaration und `@Override`-Annotationen für das Interface `IBasicGameObject`.



## Aufgabe

Ergänze die Methode `boolean checkCollision(IBasicGameObject other)` in `BasicGameObject`. Sie soll `true` zurückgeben, wenn die Hitbox dieses Objekts mit der Hitbox von `other` kollidiert.

```{.java .cb-nb line_numbers=false}
var c1 = new BasicGameObject(new V2(2,2), "XY");
var c2 = new BasicGameObject(new V2(3,2), "A");
var c3 = new BasicGameObject(new V2(4,2), "B");
println(c1.checkCollision(c2));
println(c1.checkCollision(c3));
```
**Hinweis:** Nutze `Utils.intersect` 



## Aufgabe

Ergänze die Methode `boolean checkCollision(List<IBasicGameObject>  gameObjects)` in `BasicGameObject`.
Sie prüft, ob eine Kollision mit einem anderen Objekt aus `gameObjects` vorliegt.
Die übergebene Liste enthält immer das Objekt selbst.

```{.java .cb-nb line_numbers=false}
var b1 = new BasicGameObject(new V2(3,4), "xy");
var b2 = new BasicGameObject(new V2(4,4), "#");
var b3 = new BasicGameObject(new V2(10,10), "#");
List<IBasicGameObject> allGameObjects = List.of(b1, b2, b3);
println(b1.checkCollision(allGameObjects));
println(b1.checkCollision(List.of(b1)));

```




**Hinweis:** Nutze `checkCollision`



## Aufgabe 
Ergänze die korrekte Implementierung von `isAlive(List<IBasicGameObject> gameObjects, int width, int height)` in `BasicGameObject`. Die Methode `isAlive` prüft, ob das Objekt noch Teil des Spiels ist. Es soll `true` liefern, wenn

- mindestens eine Zelle der `hitBox()` auf dem Spielfeld ist, und
- keine Kollision mit einem anderen Objekt aus `gameObjects` vorliegt

```{.java .cb-nb line_numbers=false}
var b1 = new BasicGameObject(new V2(3,4), "xy");
var b2 = new BasicGameObject(new V2(4,4), "#");
var b3 = new BasicGameObject(new V2(10,10), "#");
List<IBasicGameObject>  allGameObjects = List.of(b1, b2, b3);
println(b1.isAlive(allGameObjects, 100, 60));
println(b1.isAlive(List.of(b1), 100, 60));
println(b1.isAlive(List.of(b1), 3, 4));
```



**Hinweis:** Nutze `hitBox`, `Utils.isOnBoard` und `checkCollision`.

#  MovableGameObject

## Aufgabe 
Ergänze die Methode `boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height)` in `MovableGameObject`. 
Die Logik soll die gleiche sein wie in `BasicGameObject`, aber die Methode soll die Implementierung von `BasicGameObject` wiederverwenden.


```{.java .cb-nb line_numbers=false}
var b1 = new MovableGameObject(new V2(3,4), "xy");
var b2 = new BasicGameObject(new V2(4,4), "#");
var b3 = new BasicGameObject(new V2(10,10), "#");
List<IBasicGameObject>  allGameObjects = List.of(b1, b2, b3);
println(b1.isAlive(allGameObjects, 100, 60));
println(b1.isAlive(List.of(b1), 100, 60));
println(b1.isAlive(List.of(b1), 4, 4));
```

## Aufgabe 

Ergänze die korrekte Interface-Deklaration und `@Override`-Annotationen für das Interface `IBasicGameObject` in der Klasse `MovableGameObject`.



```{.java .cb-nb line_numbers=false}
var b1 = new MovableGameObject(new V2(3,4), "xy");
var b2 = new MovableGameObject(new V2(4,4), "#");
var b3 = new BasicGameObject(new V2(10,10), "#");
List<IBasicGameObject>  allGameObjects = List.of(b1, b2, b3);
println(b1.isAlive(allGameObjects, 100, 60));
println(b1.isAlive(List.of(b1), 100, 60));
println(b1.isAlive(List.of(b1), 4, 4));
```






# Alien

## Aufgabe
Ergänze die Methode `boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height)` in `Alien`. Verwende dabei die Methode der Klasse `BasicGameObject`.

```{.java .cb-nb line_numbers=false}
var b1 = new Alien(new V2(3,4), "xy");
var b2 = new MovableGameObject(new V2(4,4), "#");
var b3 = new BasicGameObject(new V2(10,10), "#");
List<IBasicGameObject>  allGameObjects = List.of(b1, b2, b3);
println(b1.isAlive(allGameObjects, 100, 60));
println(b1.isAlive(List.of(b1), 100, 60));
println(b1.isAlive(List.of(b1), 4, 4));
```

## Aufgabe

Ergänze die korrekte Interface-Deklaration und `@Override`-Annotationen für das Interface `IBasicGameObject` in der Klasse `Alien`.



# AlienRocket

## Aufgabe
Ergänze die Methode `boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height)` in `AlienRocket`. Verwende dabei die Methode der Klasse `BasicGameObject`.

```{.java .cb-nb line_numbers=false}
var b1 = new AlienRocket(new V2(3,4));
var b2 = new MovableGameObject(new V2(3,5), "#");
var b3 = new Alien(new V2(10,10), "#");
List<IBasicGameObject>  allGameObjects = List.of(b1, b2, b3);
println(b1.isAlive(allGameObjects, 100, 60));
println(b1.isAlive(List.of(b1), 100, 60));
println(b1.isAlive(List.of(b1), 4, 4));
```



## Aufgabe

Ergänze die korrekte Interface-Deklaration und `@Override`-Annotationen für das Interface `IBasicGameObject` in der Klasse `AlienRocket`.


# Player

## Aufgabe
Ergänze die Methode `boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height)` in `Player`. Verwende dabei die Methode der Klasse `BasicGameObject`.

```{.java .cb-nb line_numbers=false}
var b1 = new Player(new V2(3,4));
var b2 = new AlienRocket(new V2(5,4));
var b3 = new Alien(new V2(10,10), "#");
List<IBasicGameObject>  allGameObjects = List.of(b1, b2, b3);
println(b1.isAlive(allGameObjects, 100, 60));
println(b1.isAlive(List.of(b1), 100, 60));
println(b1.isAlive(List.of(b1), 4, 4));
```


## Aufgabe

Ergänze die korrekte Interface-Deklaration und `@Override`-Annotationen für das Interface `IBasicGameObject` in der Klasse `Player`.



# PlayerRocket

## Aufgabe
Ergänze die Methode `boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height)` in `PlayerRocket`. Verwende dabei die Methode der Klasse `BasicGameObject`.

```{.java .cb-nb line_numbers=false}
var b2 = new PlayerRocket(new V2(3,4));
var b1 = new Player(new V2(2,5));
var b3 = new AlienRocket(new V2(10,10));
List<IBasicGameObject>  allGameObjects = List.of(b1, b2, b3);
println(b1.isAlive(allGameObjects, 100, 60));
println(b1.isAlive(List.of(b1), 100, 60));
println(b1.isAlive(List.of(b1), 4, 4));
```

## Aufgabe

Ergänze die korrekte Interface-Deklaration und `@Override`-Annotationen für das Interface `IBasicGameObject` in der Klasse `PlayerRocket`.




# Plasma

## Aufgabe
Ergänze die Methode `boolean isAlive(List<IBasicGameObject> gameObjects, int width, int height)` in `Plasma`. Das `Plasma`-Geschoss ist am Leben, wenn ein Teil seiner Hitbox noch auf dem Spielfeld ist.

```{.java .cb-nb line_numbers=false}
var b1 = new Plasma(new V2(5,7), 3);
var b2 = new PlayerRocket(new V2(3,4));
var b3 = new Alien(new V2(10,10), "#");
List<IBasicGameObject>  allGameObjects = List.of(b1, b2, b3);
println(b1.isAlive(allGameObjects, 100, 60));
println(b1.isAlive(List.of(b1), 100, 60));
println(b1.isAlive(List.of(b1), 2, 5));
```

**Hinweis** Nutze `Utils.isOnBoard`



## Aufgabe

Ergänze die korrekte Interface-Deklaration und `@Override`-Annotationen für das Interface `IBasicGameObject` in der Klasse `Plasma`.










# Utils 

## Aufgabe
Ergänze die statische Methode `List<StringWithLocation> getStringsWithLocation(List<IBasicGameObject> basicGameObjects)` in der Klasse `Utils`. Die Methode soll die mit der Methode `show()` erzeugten Listen aller übergebenen `IBasicGameObject` zusammenfügen und als eine Liste zurückgeben.

\tiny
```{.java .cb-nb line_numbers=false}
var b1 = new BasicGameObject(new V2(3,4), "abc");
var m1 = new MovableGameObject(new V2(5,5), "Y");
List<IBasicGameObject> allGameObjects = List.of(b1, m1);
println(Utils.getStringsWithLocation(allGameObjects));
```
\normalsize



## Aufgabe
Ergänze die statische Methode `<T extends IBasicGameObject> List<T> removeDeadObjects(List<T> gameObjectsToFilter, List<IBasicGameObject> allGameObjects, int width, int height)` in der Klasse `Utils`. Die Methode soll alle Objekte aus `gameObjectsToFilter` herausfiltern, die  nicht mehr leben, und die verbleibenden Objekte zurückgeben.

\tiny
```{.java .cb-nb line_numbers=false}
var a1 = new Alien(new V2(3,4), "AB");
var a2 = new Alien(new V2(7,4), "CD");
var pr = new PlayerRocket(new V2(8,4));
List<Alien> aliens = List.of(a1, a2);
List<IBasicGameObject> allGameObjects = List.of(a1, a2, pr);
List<Alien> filteredAliens = Utils.removeDeadObjects(aliens, allGameObjects, 100, 60);
println(filteredAliens);
```
\normalsize
**Hinweis:** Nutze die Methode `isAlive!

# Model



## Aufgabe

Implementiere die Hilfsmethode `List<IBasicGameObject> gameObjects()` die alle aktuellen Spielobjekte in einer Liste zurückgibt (blocks, aliens, player).

```{.java .cb-nb line_numbers=false}
var model = new Model(40,60);
var objs = model.gameObjects();
println(objs.size());
```



## Aufgabe 

Implementiere `List<StringWithLocation> getUIState()`! . Diese Methode gibt die Liste aller mit `show()` erzeugten  `StringsWithLocation` der Spielobjekte zurück.

```{.java .cb-nb line_numbers=false}
var model = new Model(40,60);
var ui = model.getUIState();
println(ui.size());
```

**Hinweis:** Nutze `Utils.getStringsWithLocation` und die Methode `gameObjects` der Klasse `Model`.



## Aufgabe

Implementiere `boolean playerIsAlive()` die prüft, ob der Spieler noch lebt.

\tiny
```{.java .cb-nb line_numbers=false}
var model = new Model(60,40);
println(model.playerIsAlive());
var model2 = new Model(60, 40, new V2(2,4), List.of(new Alien(new V2(2,38), "a")), List.of());
println(model2.playerIsAlive());
```
\normalsize

**Hinweis:** Nutze die Methode `gameObjects` und `isAlive` der Klasse `Player`!


## Aufgabe

Implementiere die Methode `gameLost()`. Diese gibt zurück, ob das Spiel verloren wurde. Dies ist der Fall, wenn Aliens die letzte Reihe erreicht haben oder der Player nicht mehr lebt.

```{.java .cb-nb line_numbers=false}
var m1 = new Model(60,40);
println(m1.gameLost());

var aliens = List.of(new Alien(new V2(2,38), "a\nb"));
var m2 = new Model(60,40, new V2(5,4), aliens, List.of());
println(m2.gameLost());
```

**Hinweis:** Nutze die Methode `playerIsAlive` und `aliensAreInLastLine` der Klasse `AlienSwarm`!


## Aufgabe

Implementiere `boolean gameOngoing()` so, dass `true` zurückgegeben wird, solange das Spiel nicht gewonnen oder verloren ist.

```{.java .cb-nb line_numbers=false}
var m = new Model(60,40);
println(m.gameOngoing());
var mWon = new Model(60,40, new V2(5,4), List.of(), List.of());
println(mWon.gameOngoing());
```


# Rocket

## Aufgabe
Lege das Interface `Rocket`  an. Das Interface  soll die folgenden Methoden deklarieren:

- `Rocket move()`
- `boolean isPlayerRocket()`

<!-- soll `IBasicGameObject` erweitern und die -->

## Aufgabe
Ergänze bei den Klassen, die Raketen repräsentieren (`PlayerRocket`, `AlienRocket`, `Plasma`) die Interface-Deklaration und die `@Override`-Annotation.

```{.java .cb-nb line_numbers=false}
var pr = new PlayerRocket(new V2(10,5));
var ar = new AlienRocket(new V2(5,5));
var pl = new Plasma(new V2(5,5), 3);
List<Rocket> rockets = List.of(pr, ar, pl);
for (var r: rockets){
    println(r.isPlayerRocket());
}
```


# Utils

## Aufgabe
Ergänze die statische Methode `List<Rocket> move(List<Rocket> rockets)` in der Klasse `Utils`. Die Methode soll jede Rakete in der übergebenen Liste bewegen und die resultierende Liste zurückgeben.

\tiny
```{.java .cb-nb line_numbers=false}
var r1 = new PlayerRocket(new V2(10,10));
var r2 = new AlienRocket(new V2(5,5));
List<Rocket> rockets = List.of(r1, r2);
var moved = Utils.move(rockets);
println(moved);
```
\normalsize


**Hinweis** Nutze die Methode `move`!

## Aufgabe
Ergänze die statische Methode `boolean containsNoPlayerRocket(List<Rocket> rockets)` in der Klasse `Utils`. Die Methode soll `true` zurückgeben, wenn in der Liste keine `Rocket` existiert, bei der `isPlayerRocket()` den Wert `true` zurück gibt.

```{.java .cb-nb line_numbers=false}
var r1 = new PlayerRocket(new V2(10,10));
var r2 = new AlienRocket(new V2(5,5));
List<Rocket> rockets1 = List.of(r2);
println(Utils.containsNoPlayerRocket(rockets1)); 
List<Rocket> rockets2 = List.of(r1, r2);
println(Utils.containsNoPlayerRocket(rockets2));
```


# Model

# Aufgabe

Ergänze in der Klasse `Model` die Eigenschaft `rockets` als `List<Rocket>`. Die Methode `restart` von `Model` soll eine leere Liste von Raketen initialisieren.

```{.java .cb-nb line_numbers=false}
var model = new Model(60, 40);
model.restart();
println(model.rockets);
```


## Aufgabe

Ergänze den zweiten Konstruktor um einen Parameter  `rockets`.
Der Konstruktor soll das Feld entsprechend setzen. Die gesamte Signatur ist

`public Model(int width, int height, V2 playerPos, List<Alien> alienSwarm, List<BasicGameObject> blocks, List<Rocket> rockets)`

\tiny
```{.java .cb-nb line_numbers=false}
var aliens = List.of(new Alien(new V2(2,5), "A"));
var blocks = List.of(new BasicGameObject(new V2(1,1), "#"));
List<Rocket> rockets = List.of(new PlayerRocket(new V2(3,3)));
var model = new Model(60, 40, new V2(50, 38), aliens, blocks, rockets);
println(model.player);
println(model.alienSwarm);
println(model.blocks);
println(model.rockets); 
```
\normalsize




# Aufgabe
Ergänze in der Klasse `Model` die Methode `move` so, dass zusätzlich alle Raketen in `rockets` bewegt und die Liste durch die neue Liste ersetzt wird.

\tiny
```{.java .cb-nb line_numbers=false}
var model = new Model(60, 40);
model.move('d');
println(model.player);
println(model.alienSwarm);
println(model.rockets);
```
\normalsize


**Hinweis:** Nutze `Utils.move` für die Raketen.


<!-- 
## Aufgabe

Implementiere `boolean gameWon()` so, dass `true` zurückgegeben wird, wenn keine Aliens mehr vorhanden sind.

```{.java .cb-nb line_numbers=false}
var model1 = new Model(10, 10, new V2(5,8), List.of(), List.of(), List.of());
println(model1.gameWon());
var model2 = new Model(10, 10, new V2(5,8), List.of(new Alien(new V2(2,2),"a")), List.of(), List.of());
println(model2.gameWon());
```


## Aufgabe

Implementiere `String getEndMessage()` so, dass bei Gewinn "You won!" zurückgegeben wird, sonst "You lost!".

```{.java .cb-nb line_numbers=false}
var m1 = new Model(10,10, new V2(5,8), List.of(), List.of(), List.of());
println(m1.getEndMessage());
var m2 = new Model(10,10, new V2(5,8), List.of(new Alien(new V2(1,1),"a")), List.of(), List.of());
println(m2.getEndMessage());
``` -->








# Rocket

## Aufgabe

Ergänze beim Interface `Rocket`, dass dieses das Interface `IBasicGameObject` erweitert.


# Model

## Aufgabe

Ergänze die Methode `gameObjects` so, dass auch alle Rakten zurückgegebn werden.

\tiny
```{.java .cb-nb line_numbers=false}
List<Rocket> rockets = List.of(new PlayerRocket(new V2(3,3)));
var model = new Model(60, 40, new V2(50, 38), List.of(), List.of(), rockets);
println(model.gameObjects());
```
\normalsize



## Aufgabe


Implementiere `removeDeadObjects()` so, dass

 
- alle Blöcke gefiltert werden
- alle Raketen gefiltert werden
- die Aliens im `alienSwarm` gefiltert werden

\tiny
```{.java .cb-nb line_numbers=false}
var aliens = List.of(new Alien(new V2(2,5), "A"));
var blocks = List.of(new BasicGameObject(new V2(1,1), "#"), new BasicGameObject(new V2(2,1), "#"));
List<Rocket> rockets = List.of(new PlayerRocket(new V2(2, 5)), new AlienRocket(new V2(1, 1)));
var model = new Model(60, 40, new V2(50, 38), aliens, blocks, rockets);
model.removeDeadObjects();
println(model.alienSwarm);
println(model.blocks);
println(model.rockets); 
```
\normalsize

Hinweis: Nutze `Utils.removeDeadObjects` für die Blöcke und Raketen, sowie die Methode `removeDeadAliens` von `AlienSwarm` für die Aliens.





# Shooting 

## Aufgabe

Definiere das Interface `Shooting` und deklariere die Methode

- `Rocket shoot()`

## Aufgabe


Ergänze in den Klassen `Alien` und `Player`  die Interface-Deklaration und die `@Override`-Annotationen für das Interface `Shooting`.

```{.java .cb-nb line_numbers=false}
var alien = new Alien(new V2(5,4), "A");
var player = new Player(new V2(10,18));
List<Shooting> shooters = List.of(alien, player);
```




# RocketFactory

## Aufgabe 

Lege die Klasse `RocketFactory`  an!


## Aufgabe
Implementiere die statische Methode

- `List<Shooting> getShootingObjects(List<Rocket> rockets, Alien shootingAlien, Player player, char pressedKey)`

Die Methode sammelt alle Objekte, die in diesem Update-Schritt schießen dürfen:

- Falls `shootingAlien` nicht `null` ist, wird dieses Alien zur Rückgabeliste hinzugefügt.
- Der Spieler darf nur schießen, wenn aktuell keine Spieler-Rakete in `rockets` vorhanden ist (Nutze `Utils.containsNoPlayerRocket`).
- Ist `pressedKey == 'k'` und der Spieler darf schießen, füge den `player` hinzu.
- Ist `pressedKey == 'l'` und der Spieler darf schießen, füge stattdessen eine neue `InvisiblePlasmaCannon` an der Position des `player` hinzu.

\tiny
```{.java .cb-nb line_numbers=false}
// Beispiel 1: Nur Alien kann schießen
var a = new Alien(new V2(5,4), "A");
var shooters = RocketFactory.getShootingObjects(List.of(), a, new Player(new V2(10,18)), ' ');
println(shooters);
```

```{.java .cb-nb line_numbers=false}
// Beispiel 2: Spieler schießt mit 'k'
var p = new Player(new V2(10,18));
var shooters2 = RocketFactory.getShootingObjects(List.of(), null, p, 'k');
println(shooters2);
```

```{.java .cb-nb line_numbers=false}
// Beispiel 3: Spieler schießt Plasma mit 'l'
var p2 = new Player(new V2(10,18));
var shooters3 = RocketFactory.getShootingObjects(List.of(), null, p2, 'l');
println(shooters3);
```
\normalsize
## Aufgabe
Implementiere die statische Methode

- `List<Rocket> getNewRockets(List<Shooting> shootingObjects)`

Die Methode ruft für jedes `Shooting`-Objekt `shoot()` auf und sammelt die erzeugten `Rocket`-Objekte in einer Liste, die zurückgegeben wird.

\tiny
```{.java .cb-nb line_numbers=false}
var alien = new Alien(new V2(5,4), "A");
var player = new Player(new V2(10,18));
List<Shooting> shooters = List.of(alien, player);
var rockets = RocketFactory.getNewRockets(shooters);
println(rockets);
```

```{.java .cb-nb line_numbers=false}
// leerer Input => keine neuen Raketen
var empty = RocketFactory.getNewRockets(List.of());
println(empty);
```
\normalsize

## Aufgabe
Implementiere die statische Methode

- `List<Rocket> getNewRockets(List<Rocket> existingRockets, Alien shootingAlien, Player player, char key)`

Die Methode soll die neuen Raketen erzeugen, die in diesem Update-Schritt zum Spiel hinzugefügt werden (konsistent mit den beiden obigen Helfern):
- Nutze `getShootingObjects(...)` um die schießenden Objekte zu bestimmen.
- Verwende dann `getNewRockets(List<Shooting>)`, um aus den Schützen die konkret neu erzeugten Raketen zu erhalten.

\tiny
```{.java .cb-nb line_numbers=false}
// Beispiel 1: Alien schießt
var a = new Alien(new V2(5,4), "A");
var newRockets = RocketFactory.getNewRockets(List.of(), a, new Player(new V2(10,18)), ' ');
println(newRockets.size());
```

```{.java .cb-nb line_numbers=false}
// Beispiel 2: Player schießt mit 'k'
var p = new Player(new V2(10,18));
var newRockets2 = RocketFactory.getNewRockets(List.of(), null, p, 'k');
println(newRockets2.size());
```

```{.java .cb-nb line_numbers=false}
var a2 = new Alien(new V2(2,3), "A");
var p2 = new Player(new V2(10,18));
var both = RocketFactory.getNewRockets(List.of(), a2, p2, 'k');
println(both.size());
```
\normalsize

## Aufgabe
Implementiere die statische Methode

- `List<Rocket> addNewRockets(List<Rocket> rockets, Alien shootingAlien, Player player, char key)`

Die Methode soll die vorhandenen Raketen (`rockets`) mit den in diesem Update-Schritt neu erzeugten Raketen zusammenführen:

- Gib eine Liste zurück, die zuerst die bisherigen Raketen und danach die neuen Raketen enthält.

	iny
```{.java .cb-nb line_numbers=false}
// Beispiel 1: bestehende Raketen + neue Alien-Rakete
var existing = List.of(new PlayerRocket(new V2(10,10)));
var a = new Alien(new V2(5,4), "A");
var combined = RocketFactory.addNewRockets(existing, a, new Player(new V2(10,18)), ' ');
println(combined.size());
```

```{.java .cb-nb line_numbers=false}
// Beispiel 2: Spieler schießt mit 'k' (falls erlaubt)
var p = new Player(new V2(10,18));
var combined2 = RocketFactory.addNewRockets(List.of(), null, p, 'k');
println(combined2.size());
```
\normalsize


**Hinweis:**  Nutze `getNewRockets`, um die neuen Raketen zu erzeugen.

# Model 

## Aufgabe

Implementiere die Methode `update(char key)` in der Klasse `Model`.

Die Methode soll in dieser Reihenfolge arbeiten:

- Tote Objekte entfernen 
- Objekte bewegen
- Neue Raketen erzeugen und zur Raketenliste hinzufügen

\tiny
```{.java .cb-nb line_numbers=false}
var model = new Model(60,40);
model.update('a');
println(model.player);
println(model.rockets.size());
```

```{.java .cb-nb line_numbers=false}
var model2 = new Model(60,40);
// Spieler versucht zu schießen (falls noch keine Spieler-Rakete existiert)
model2.update('k');
println(model2.rockets.size());
// Noch ein Update: je nach Situation wird ggf. eine weitere Rakete erzeugt
model2.update('k');
println(model2.rockets.size());
```
\normalsize

**Hinweis:* Nutze `removeDeadObjects`, `move`, und `RocketFactory.addNewRockets()` und `addAll` für die Raketenliste.


# Controller — runGame

## Aufgabe

Implementiere die Methode `runGame()` in der Klasse `Controller`.

Die Methode enthält die Hauptspielschleife:

1. Spiele so lange bis `model.gameOngoing` den Wert `false` zurückgibt oder die Taste `'q'` gedrückt wurde.
2. In jedem Schleifendurchlauf: - zeichne den aktuellen UI-Zustand
                                - lese die gedrückte Taste (`tui.getPressedKey()`) 
                                - aktualisiere den Spielzustand
3. Nach Beenden der Schleife soll die Abschlussnachricht angezeigt und das Terminal geschlossen werden.


**Hinweis:** Nutze die Methoden `getUIState`, `update`, `gameOngoing` und `getEndMessage` der Klasse `Model` und  `getPressedKey`, `printString` und `close` der Klasse `TUI`.

```java
var controller = new Controller(100, 60);
controller.runGame();
```




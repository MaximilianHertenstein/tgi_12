import static java.lang.IO.println;

record Student(String name, int age, Subject mainSubject) {


    void greet(){
        var greeting = getGreeting();
        println(greeting);
    }
    String getGreeting() {
        return ("Hello, my name is " + name + ", I am " + age + " years old");
    }

    int getGradeOfMainSubject() {
        return  mainSubject.grade();
    }

    void implementSnakeAndPlay() {
        Game snakeGame = new Game("Snake");
        snakeGame.play();
    }


    void sayHelloOrImplementAndPlayGame(int nerdinessLevel) {
        if (nerdinessLevel <= 3) {
            greet();
        }
        else  {
            implementSnakeAndPlay();
        }
    }

//    void sayHello(){
//        println("Hello!");
//    }
//
//    void sayGoodMorning(){
//        println("Hello!");
//    }
//
//    void greetOtherStudent(int time ) {
//        if (time <= 10) {
//           sayGoodMorning();
//        }
//        else  {
//            sayHello();
//        }
//    }


}

record Game(String title) {
    public void play() {
        println("Playing " + title +  "!");
    }
};
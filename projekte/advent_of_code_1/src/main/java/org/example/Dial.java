package org.example;

import java.util.List;


public class Dial  {

    private int pos;
    private int zeroCount;


    public Dial(){
        this(50,0);
    }

    public Dial(int pos, int zeroCount){
        this.pos = pos;
        this.zeroCount = zeroCount;
    }



    private static int normalize(int pos){
        int dialSize = 100;
        while (pos < 0){
            pos = pos + dialSize;
        }
        while (pos > 99){
            pos = pos - dialSize;
        }
        return pos;
    }

    static int nextPos(String command, int pos){
        int change = Utils.computeChange(command);
        return normalize(pos + change);
    }


    public  void turn(String command){
        pos = nextPos(command, pos);
        if (pos == 0){
            zeroCount++;
        }
    }


    public void turn(List<String> commands){
        for (var command: commands){
            turn(command);
        };
    }


    public int zeroCount() {
        return zeroCount;
    }
}

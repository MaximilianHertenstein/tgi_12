package org.example;

import static org.junit.jupiter.api.Assertions.*;

//class DialTest {
//
//    @org.junit.jupiter.api.Test
//    void turn1() {
//        var dial = new Dial();
//        dial = dial.turn2("L68");
//        assertEquals(new Dial(82,1),dial  );
//
//    }
//    @org.junit.jupiter.api.Test
//
//    void turn2(){
//        var dial = new Dial(82,1);
//        dial = dial.turn2("L30");
//        assertEquals(new Dial(52,1),dial);
//    }
//    @org.junit.jupiter.api.Test
//
//    void turn3(){
//        var dial = new Dial(52,1);
//        dial = dial.turn2("R48");
//        assertEquals(new Dial(0,2),dial  );
//    }
//    @org.junit.jupiter.api.Test
//
//    void turn4(){
//        var dial = new Dial(0,2);
//        dial = dial.turn2("L5");
//        assertEquals(new Dial(95,2),dial  );
//    }
//
//    @org.junit.jupiter.api.Test
//    void turn5(){
//        var dial = new Dial(95,2);
//        dial = dial.turn2("R60");
//        assertEquals(new Dial(55,3),dial  );
//    }
//
//    @org.junit.jupiter.api.Test
//    void turn6(){
//        var dial = new Dial(55,3);
//        dial = dial.turn2("L55");
//        assertEquals(new Dial(0,4),dial  );
//    }
//
//    @org.junit.jupiter.api.Test
//    void turn7(){
//        var dial = new Dial(0,4);
//        dial = dial.turn2("L1");
//        assertEquals(new Dial(99,4),dial  );
//    }
//
//    @org.junit.jupiter.api.Test
//    void turn8(){
//        var dial = new Dial(99,4);
//        dial = dial.turn2("L99");
//        assertEquals(new Dial(0,5),dial  );
//    }
//
//    @org.junit.jupiter.api.Test
//    void turn9(){
//        var dial = new Dial(0,5);
//        dial = dial.turn2("R14");
//        assertEquals(new Dial(14,5),dial  );
//    }
//
//    @org.junit.jupiter.api.Test
//    void turn10(){
//        var dial = new Dial(14,5);
//        dial = dial.turn2("L82");
//        assertEquals(new Dial(32,6),dial  );
//    }
//
//
//
//    @org.junit.jupiter.api.Test
//    void turn2_test() {
//        var dial = new Dial();
//        dial = dial.turn2("R1000");
//        assertEquals(new Dial(50,10),dial  );
//    }
//
//    @org.junit.jupiter.api.Test
//    void turn3_test() {
//        var dial = new Dial(0, 0);
//        dial = dial.turn2("R1000");
//        assertEquals(new Dial(0,10),dial  );
//    }
//}
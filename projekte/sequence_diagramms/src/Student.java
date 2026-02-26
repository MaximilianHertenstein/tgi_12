import java.util.List;

import static java.lang.IO.println;

record Student(String name, int age, Subject mainSubject, List<Subject> subjects) {


    void greet(){
        var greeting = getGreeting();
        println(greeting);
    }
    String getGreeting() {
        return ("Hello, my name is " + name + ", I am " + age + " years old");
    }

    void printNameOfMainSubject() {
          println("My main subject is " + mainSubject.name());
    }

    void implementSnakeAndPlay() {
        Game snakeGame = new Game("Snake");
        snakeGame.play();
    }


    void sayHelloOrImplementAndPlayGame(int extrovertednessLevel) {
        if (extrovertednessLevel > 3) {
            greet();
        }
        else  {
            implementSnakeAndPlay();
        }
    }

    void sayHelloOrMainSubject(int weirdnessLevel) {
        if (weirdnessLevel > 3) {
            printNameOfMainSubject();
        }
        else  {
            greet();
        }
    }

    void printSubjects() {
        for (var subject : subjects) {
            String subjectName = subject.name();
            println(subjectName);
        }
    }


    void printSubjects2() {
        for (int i = 0; i < subjects.size(); i++) {
            Subject subject = subjects.get(i);
            String subjectName = subject.name();
            println("Fach"  + i +  ": "  + subjectName);
        }
    }



    void printSubjects3() {
        int i = 0;
        while (i < subjects.size()) {
            Subject subject = subjects.get(i);
            String subjectName = subject.name();
            println("Fach"  + i +  ": "  + subjectName);
            i++;
        }
    }


    void removeExtensiveSubjects(){

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
import static java.lang.IO.print;
import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.

}


interface Greets {
    void greet();
}

record Teacher(String firstName, String secondName)implements Greets{
    @Override
    public void greet() {
        println("Hello, I am " + firstName + " " + secondName);
    }
}

record Student(String firstName, String secondName, int age) implements Greets  {
    @Override
    public void greet() {
        println("Yo, I am " + firstName);
    }
}


class Utils {

    void showTeacherGreetings(List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            teacher.greet();
        }
    }

    void showStudentGreetings(List<Student> students) {
        for (Student student : students) {
            student.greet();
        }
    }

    void showGreetings(List<? extends Greets> greets) {
        for (Greets greet : greets) {
            greet.greet();
        }
    }


}

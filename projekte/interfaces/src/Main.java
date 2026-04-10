import static java.lang.IO.print;
import static java.lang.IO.println;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.

}

        interface Greetable {
            void greet();
            String lastName();
        }

        record Teacher(String firstName, String lastName) implements Greetable {
            @Override
            public void greet() {
                println("Hello, I am " + firstName + " " + lastName);
            }
        }

        record Student(String firstName, String lastName, int age) implements Greetable {
            @Override
            public void greet() {
                println("Yo, I am " + firstName);
            }
        }

        class GreetingUtils {


            static void letTeacherGreetTwoTimes(Teacher teacher){
                teacher.greet();
                teacher.greet();
            }

            static void letStudentGreetTwoTimes(Student student){
                student.greet();
                student.greet();
            }


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

            void showGreetings(List<Greetable> greetables) {
                for (Greetable greetable : greetables) {
                    greetable.greet();
                }
            }

            static <T extends Greetable> List<T> filterMüller(List<T> greetables) {
                List<T> result = new ArrayList<>();
                for (T greetable : greetables) {
                    if (greetable.lastName().equals("Müller")) {
                        result.add(greetable);
                    }
                }
                return result;
            }

        }
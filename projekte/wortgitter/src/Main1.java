import static java.lang.IO.println;

void main() {
    
}

record Subject(String name, int grade){};

record Student(List<Subject> subjects) {
    void hasUnderCourse(){
        boolean found = false;
        for (var subject: subjects){
            println("Prüfe: " + subject.name());
            if (subject.grade() < 5){
                found = true;
                break;
            }
        }
        if (found){
            println("Hat einen Unterkurs");
        }
        else {
            println("Hat keinen Unterkurs");
        }
    }
}
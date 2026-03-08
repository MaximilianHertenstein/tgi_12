import io.javalin.Javalin;
import org.example.JavalinConfigurator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

        void main() {
            Javalin app = Javalin.create(JavalinConfigurator::configure);

                    app.start(7070);
        }



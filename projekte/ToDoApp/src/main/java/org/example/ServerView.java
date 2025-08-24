package org.example;

import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public class ServerView {



    public void renderMain(Context ctx, List<ToDoItem> toDoItems, int indexOfToDo, int countOfActiveToDos, String appliedFilter) {
        var map = Map.of("toDoItems", toDoItems,"indexOfEditing", indexOfToDo, "countOfActiveToDos", countOfActiveToDos, "appliedFilter", appliedFilter);
        ctx.render("mainPage.jte", map);
    }


}

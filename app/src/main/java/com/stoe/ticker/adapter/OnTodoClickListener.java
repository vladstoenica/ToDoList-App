package com.stoe.ticker.adapter;

import com.stoe.ticker.model.Task;

//Always use interfaces when passing arround click events - aici ne-a ajutat sa ducem parametrii position si task in main
//dupa, de acolo putem sa luam gen task.getTask() si alte lucruri care tin de parametrii astia
public interface OnTodoClickListener {
    void onTodoClick(Task task);
    void onTodoRadioButtonClick(Task task);
}

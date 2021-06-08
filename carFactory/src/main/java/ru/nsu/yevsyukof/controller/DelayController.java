package ru.nsu.yevsyukof.controller;

import ru.nsu.yevsyukof.model.factory.Delay;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DelayController implements ChangeListener {

    private final Delay managedDelay;

    public DelayController(Delay managedDelay) {
        this.managedDelay = managedDelay;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        managedDelay.setDelay(((JSlider) e.getSource()).getValue());
    }
}

package org.nullapp.sorrowland.app.controller;

import org.nullapp.sorrowland.app.view.intro.LogoView;
import org.nullapp.appCore.process.view.AbstractView;
import org.nullapp.sorrowland.app.manager.Manager;
import org.nullapp.appCore.process.controller.Controller;
import org.nullapp.appCore.service.AppContext;

import java.awt.event.KeyEvent;

public class IntroController extends Controller<AbstractView> {

  final static public String LOGO = "LOGO";

  public IntroController(AppContext context) {
    super(context);
  }

  @Override
  protected void doInitialize() {
    registerView(LOGO, LogoView.class, getContext(), this);
    setActiveView(LOGO);
  }

  @Override
  public void doUpdate(float nano) {
    if (getInputKey().getKey(KeyEvent.VK_2)) {
      getAppManager().setActiveController(Manager.STATE_MENU);
    }
  }

}

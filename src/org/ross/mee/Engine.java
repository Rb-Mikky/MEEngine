package org.ross.mee;

import org.ross.mee.graphics.Window;
import org.ross.mee.input.Input;
import org.ross.mee.input.Keyboard;

public class Engine 
{
	public Engine(App app)
	{
		app.Init();
		Window.Init(app.getTitle(), 1280, 720);
		while (Window.isActive())
		{
			if (Input.getKeyboard().isKeyPressed(Keyboard.KEY_ESCAPE)){break;}
			app.Process();
			app.Draw();
			Window.SwapBuffers();
			Input.PollEvents();
		}
		app.Clean();
		Window.Terminate();
	}
}

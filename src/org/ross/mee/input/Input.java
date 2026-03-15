package org.ross.mee.input;

import static org.lwjgl.glfw.GLFW.*;

public class Input 
{
	private static Keyboard keyboard_input = new Keyboard();
	private static Mouse mouse_input = new Mouse();
	
	public static void PollEvents()
	{
		glfwPollEvents();
		mouse_input.Process();
		keyboard_input.Process();
	}
	
	public static Keyboard getKeyboard()
	{
		return keyboard_input;
	}
	
	public static Mouse getMouse()
	{
		return mouse_input;
	}
	
	
}

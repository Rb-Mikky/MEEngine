package org.ross.mee.input;

public interface InputEvent 
{
	public String getTriggerName();
	public void onKeyPressed(int key);
	public void onKeyReleased(int key);
}

package org.ross.mee.graphics;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glShaderSource;
import java.nio.charset.StandardCharsets;
import org.ross.mee.Engine;

public class Shader 
{
	public static final int VERTEX = GL_VERTEX_SHADER;
	public static final int FRAGMENT = GL_FRAGMENT_SHADER;
	private int type,id;
	private String data,log;
	
	public Shader(String filename, int type) throws Exception
	{
		this.type=type;
		id=glCreateShader(type);
		data = new String(getClass().getResourceAsStream(Engine.render_shader_dir+filename).readAllBytes(),StandardCharsets.UTF_8);
		glShaderSource(id, data);
		glCompileShader(id);
		log=glGetShaderInfoLog(id);
		if (!log.isEmpty())
		{
			throw new Exception(log);
		}
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void Delete()
	{
		glDeleteShader(id);
	}
}

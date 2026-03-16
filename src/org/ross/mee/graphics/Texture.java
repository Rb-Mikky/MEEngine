package org.ross.mee.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.EXTTextureFilterAnisotropic.*;
import static org.lwjgl.stb.STBImage.*;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import org.lwjgl.system.MemoryUtil;

public class Texture 
{
	public static final int SLOT_0=GL_TEXTURE0;
	public static final int SLOT_1=GL_TEXTURE1;
	public static final int SLOT_2=GL_TEXTURE2;
	public static final int SLOT_3=GL_TEXTURE3;
	public static final int SLOT_4=GL_TEXTURE4;
	public static final int SLOT_5=GL_TEXTURE5;
	public static final int SLOT_6=GL_TEXTURE6;
	public static final int SLOT_7=GL_TEXTURE7;
	public static final int SLOT_8=GL_TEXTURE8;
	public static final int SLOT_9=GL_TEXTURE9;
	public static final int SLOT_10=GL_TEXTURE10;
	public static final int SLOT_11=GL_TEXTURE11;
	public static final int SLOT_12=GL_TEXTURE12;
	public static final int SLOT_13=GL_TEXTURE13;
	public static final int SLOT_14=GL_TEXTURE14;
	public static final int SLOT_15=GL_TEXTURE15;
	public static final int SLOT_16=GL_TEXTURE16;
	public static final int SLOT_17=GL_TEXTURE17;
	public static final int SLOT_18=GL_TEXTURE18;
	public static final int SLOT_19=GL_TEXTURE19;
	public static final int SLOT_20=GL_TEXTURE20;
	public static final int SLOT_21=GL_TEXTURE21;
	public static final int SLOT_22=GL_TEXTURE22;
	public static final int SLOT_23=GL_TEXTURE23;
	public static final int SLOT_24=GL_TEXTURE24;
	public static final int SLOT_25=GL_TEXTURE25;
	public static final int SLOT_26=GL_TEXTURE26;
	public static final int SLOT_27=GL_TEXTURE27;
	public static final int SLOT_28=GL_TEXTURE28;
	public static final int SLOT_29=GL_TEXTURE29;
	public static final int SLOT_30=GL_TEXTURE30;
	public static final int SLOT_31=GL_TEXTURE31;
	
	int id;
	int width;
	int height;
	int format;
	int src_format;
	
	public Texture(String filename)
	{
		ByteBuffer image = loadDataFromFile(filename);
		id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAX_ANISOTROPY_EXT, glGetFloat(GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT));
		glTexImage2D(GL_TEXTURE_2D, 0, format, width, height, 0, src_format, GL_UNSIGNED_BYTE, image);
		glGenerateMipmap(GL_TEXTURE_2D);
		stbi_image_free(image);
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public void useTexture()
	{
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void useTexture(int slot)
	{
		glActiveTexture(slot);
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void deleteTexture()
	{
		glDeleteTextures(id);
	}
	
	private ByteBuffer loadDataFromFile(String filename)
	{
		byte[] data = null;
		try 
		{
			data = Files.readAllBytes(new File(filename).toPath());
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		ByteBuffer bb = MemoryUtil.memAlloc(data.length);
		bb.put(data);
		bb.flip();
		int[] x = new int[1]; // width
		int[] y = new int[1]; // height
		int[] comp = new int[1]; // 3 = RGB / 4 = RGBA
		stbi_info_from_memory(bb, x, y, comp);
		width=x[0];
		height=y[0];
		format=GL_SRGB;
		src_format=GL_RGB;
		if (comp[0]==4)
		{
			src_format=GL_RGBA;
			format=GL_SRGB_ALPHA;
		}
		stbi_set_flip_vertically_on_load(true);
		ByteBuffer image = stbi_load_from_memory(bb, x, y, comp, comp[0]);
		MemoryUtil.memFree(bb);
		return image;
	}
}

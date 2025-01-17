package com.mideas.rpg.v2.chat.lua;

import java.util.ArrayList;
import java.util.HashMap;

import com.mideas.rpg.v2.Mideas;
import com.mideas.rpg.v2.chat.ChatFrame;
import com.mideas.rpg.v2.chat.MessageType;
import com.mideas.rpg.v2.utils.InputBox;
import com.mideas.rpg.v2.utils.UIElement;

public class StoreLuaFunctions {

	private final static HashMap<String, LuaFunction> functionMap = new HashMap<String, LuaFunction>();
	private final static LuaFunction getMouseFocus = new LuaFunction("GetMouseFocus")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			System.out.println("GetMouseFocus called with list: " + args);
			System.out.println("Focus: " + Mideas.getHoveredElement());
			return (Mideas.getHoveredElement());
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object == null);
		}
	};
	private final static LuaFunction random = new LuaFunction("Random")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			System.out.println("Random called with list: " + args);
			System.out.println(args.get(0).getClass());
			if (args.size() == 2)
			{
				if (args.get(0) instanceof Integer && args.get(1) instanceof Integer)
					return ((int)args.get(0) + ((int)args.get(1) - (int)args.get(0)) * Math.random());
			}
			return (Math.random());
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object == null);
		}
	};
	private final static LuaFunction getName = new LuaFunction("GetName")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			System.out.println("GetName called with list: " + args);
			return (((UIElement)object).getName());
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof UIElement);
		}
	};
	private final static LuaFunction setName = new LuaFunction("SetName")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			if (args.size() == 0)
				return (null);
			((UIElement)object).setName((String)args.get(0));
			return (null);
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof UIElement);
		}
	};
	private final static LuaFunction getWidth = new LuaFunction("GetWidth")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			return (((UIElement)object).getWidth());
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof UIElement);
		}
	};
	private final static LuaFunction getHeight = new LuaFunction("GetHeight")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			return (((UIElement)object).getHeight());
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof UIElement);
		}
	};
	private final static LuaFunction setWidth = new LuaFunction("SetWidth")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			return (null);
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof UIElement);
		}
	};
	private final static LuaFunction setText = new LuaFunction("SetText")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			if (args == null || args.size() == 0)
				return (null);
			((InputBox)object).setText((String)args.get(0));
			return (null);
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof InputBox);
		}
	};
	private final static LuaFunction setX = new LuaFunction("SetX")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			if (args == null || args.size() == 0 || !(args.get(0) instanceof Integer))
				return (null);
			((UIElement)object).setX((Integer)args.get(0));
			return (null);
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof UIElement);
		}
	};
	private final static LuaFunction setY = new LuaFunction("SetY")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			if (args == null || args.size() == 0 || !(args.get(0) instanceof Integer))
				return (null);
			((UIElement)object).setY((Integer)args.get(0));
			return (null);
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof UIElement);
		}
	};
	private final static LuaFunction setHeight = new LuaFunction("SetHeight")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			if (args == null || args.size() == 0 || !(args.get(0) instanceof Integer))
				return (null);
			((UIElement)object).setHeight((Integer)args.get(0));
			return (null);
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof UIElement);
		}
	};
	private final static LuaFunction sendChatMessage = new LuaFunction("SendChatMessage")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			if (args == null || args.size() < 3)
				return (null);
			String message = (String)args.get(0);
			MessageType type = MessageType.getMessageType((String)args.get(1));
			if (type == null)
				return (null);
			String target = (String)args.get(2);
			ChatFrame.sendChatMessage(message, type, target);
			return (null);
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object == null);
		}
	};
	private final static LuaFunction getObjectType = new LuaFunction("GetObjectType")
	{
	
		@Override
		public Object handleFunction(Object object, ArrayList<Object> args)
		{
			return (((UIElement)object).getElementType());
		}
		
		@Override
		public boolean isFunctionVisible(Object object)
		{
			return (object instanceof UIElement);
		}
	};
	
	public static void addFunction(LuaFunction function)
	{
		functionMap.put(function.getName(), function);
	}
	
	public static LuaFunction getFunction(Object object, String functionName)
	{
		System.out.println("StoreLuaFunctions:GetFunction:functionName: '" + functionName + "', object: '" + object + "'");
		LuaFunction function = functionMap.get(functionName);
		if (function != null && function.isFunctionVisible(object))
			return (function);
		return (null);
	}
}

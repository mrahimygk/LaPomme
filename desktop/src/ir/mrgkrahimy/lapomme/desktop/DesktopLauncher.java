package ir.mrgkrahimy.lapomme.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ir.mrgkrahimy.lapomme.base.TheGame;
import ir.mrgkrahimy.lapomme.helpers.Consts;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Consts.W;
        config.height = Consts.H;
        new LwjglApplication(new TheGame(), config);
	}
}

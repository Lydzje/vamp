package vampire.utils.listeners;

import vampire.graphics.ui.UIButton;

public class UIButtonListener {

	public void entered(UIButton button) {
		// if (button.isImageMode()) button.setImage(ImageUtils.changeBrightness(
		// (BufferedImage) button.getImage(), 20));
		// else button.setSprite(ImageUtils.changeBrightness(button.getSprite(), 20));
	}

	public void exited(UIButton button) {
		// if (button.isImageMode()) button.setImage(button.getImage());
		// else button.setSprite(button.getSprite());
	}

	public void pressed(UIButton button) {
		// if (button.isImageMode()) button.setImage(ImageUtils.changeBrightness(
		// (BufferedImage) button.getImage(), -50));
		// else button.setSprite(ImageUtils.changeBrightness(button.getSprite(), -50));
	}

	public void released(UIButton button) {
		// if (button.isImageMode()) button.setImage(ImageUtils.changeBrightness(
		// (BufferedImage) button.getImage(), 20));
		// else button.setSprite(ImageUtils.changeBrightness(button.getSprite(), 20));
	}

}

package ui.utils;

import javax.swing.Icon;
import javax.swing.JButton;

import ui.utils.IconConstants;

public final class ButtonFacotry {
	private ButtonFacotry() {
	}
	
	public static JButton createSimpleButton(String title) {
		JButton button = new JButton(title);
		return button;
	}
	
	public static JButton createSimpleButton(Icon icon) {
		JButton button = new JButton(icon);
		return button;
	}
	
	public static JButton createSimpleButton(String title, Icon icon) {
		JButton button = new JButton(title);
		button.setIcon(icon);
		
		return button;
	}
	
	public static JButton createSimpleButton(String title, char mnemonic) {
		JButton button = new JButton(title);
		button.setMnemonic(mnemonic);
		
		return button;
	}
	
	public static JButton createSimpleButton(String title, char mnemonic, Icon icon) {
		JButton button = createSimpleButton(title, mnemonic);
		button.setIcon(icon);
		
		return button;
	}
	
	
	public static JButton createFirstButton() {
		return createSimpleButton(IconConstants.FIRST_ICON_25_25);
	}
	
	public static JButton createPreviousButton() {
		return createSimpleButton(IconConstants.PREV_ICON_25_25);
	}

	public static JButton createNextButton() {
		return createSimpleButton(IconConstants.NEXT_ICON_25_25);
	}
	
	public static JButton createLastButton() {
		return createSimpleButton(IconConstants.LAST_ICON_25_25);
	}
}

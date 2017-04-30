package com.hisen.clipboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class JavaClipboard {
	/**
	 * 设置系统剪切板内容 - 内容为文本
	 * 
	 * @param refContent
	 */
	public static void setSystemClipboard(String refContent) {
		String vc = refContent.trim();
		StringSelection ss = new StringSelection(vc);

		Clipboard sysClb = null;
		sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();
		sysClb.setContents(ss, null);
	}

	/**
	 * 获取系统剪切板内容 - 剪切板中内容为文本型
	 * 
	 * @return
	 */
	public static String getSystemClipboard() {
		Clipboard sysClb = null;
		sysClb = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable t = sysClb.getContents(null);
		try {
			if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				String text = (String) t
						.getTransferData(DataFlavor.stringFlavor);
				return text;
			}
		} catch (UnsupportedFlavorException e) {
			// System.out.println("Error tip: "+e.getMessage());
		} catch (IOException e) {
		} // System.out.println("Error tip: "+e.getMessage());
		return null;
	}

	public static void main(String[] args) {
		// 给剪切板设置文本型内容
		 JavaClipboard.setSystemClipboard("这里是新设置的剪切板的内容");
		// 获取剪切板的文本型内容
		String copyContent = JavaClipboard.getSystemClipboard();
		System.out.println(copyContent);
	}
}

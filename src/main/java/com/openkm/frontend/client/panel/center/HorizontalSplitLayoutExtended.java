/**
 * OpenKM, Open Document Management System (http://www.openkm.com)
 * Copyright (c) 2006-2015 Paco Avila & Josep Llort
 * 
 * No bytes were intentionally harmed during the development of this application.
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.openkm.frontend.client.panel.center;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * HorizontalSplitLayoutExtended
 * 
 * @author jllort
 */
public class HorizontalSplitLayoutExtended extends SplitLayoutPanel {
	private int leftWidth = 0;
	private int rightWidth = 0;
	private HorizontalResizeHandler resizeHander;

	/**
	 * HorizontalSplitPanelExtendend
	 * 
	 * @param handler
	 */
	public HorizontalSplitLayoutExtended(final HorizontalResizeHandler resizeHander) {
		super();
		this.resizeHander = resizeHander;
		setStyleName("okm-HorizontalSplitPanel");
	}
	
	@Override
	public void onResize() {
		super.onResize();
		leftWidth = Integer.parseInt(DOM.getStyleAttribute(DOM.getChild(this.getElement(), 2), "left").replace("px", "").trim());
		rightWidth = this.getOffsetWidth() - Integer.parseInt(DOM.getStyleAttribute(DOM.getChild(this.getElement(), 3), "left").replace("px", "").trim());
		if (leftWidth<0) {
			leftWidth = 0;
		}
		if (rightWidth<0) {
			rightWidth = 0;
		}
		resizeHander.onResize(leftWidth, rightWidth);
	}

	/**
	 * getLeftWidth
	 */
	public int getLeftWidth() {
		return leftWidth;
	}

	/**
	 * getRightWidth
	 */
	public int getRightWidth() {
		return rightWidth;
	}
	
	@Override
	public int getOffsetHeight() {
		int offsetHeight = super.getOffsetHeight(); // when widget is hidden value is 0
		if (offsetHeight == 0 && DOM.getStyleAttribute(this.getElement(), "height")!=null && !DOM.getStyleAttribute(this.getElement(), "height").isEmpty()) {
			offsetHeight = Integer.parseInt(DOM.getStyleAttribute(this.getElement(), "height").replaceAll("px", ""));
		}
		return offsetHeight;
	}
	
	@Override
	public int getOffsetWidth() {
		int offsetWidth = super.getOffsetWidth(); // when widget is hidden value is 0
		if (offsetWidth == 0 && DOM.getStyleAttribute(this.getElement(), "width")!=null && !DOM.getStyleAttribute(this.getElement(), "width").isEmpty()) {
			offsetWidth = Integer.parseInt(DOM.getStyleAttribute(this.getElement(), "width").replaceAll("px", ""));
		}
		return offsetWidth;
	}

	/**
	 * setSplitPosition
	 */
	public void setSplitPosition(Widget widgetBeforeTheSplitter, double size, boolean animate) {
		LayoutData layout = (LayoutData) widgetBeforeTheSplitter.getLayoutData();
		layout.oldSize = layout.size;
		layout.size = size;
		if (animate)
			animate(500);
		else
			super.forceLayout();
	}
}
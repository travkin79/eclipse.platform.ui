/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.ui.internal.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Provide a Handler for the Close Part command. This can then be bound to
 * whatever keybinding the user prefers.
 * 
 * @since 3.3
 */
public class ClosePartHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart part = null;
		IWorkbenchWindow window = null;
		if (event.getApplicationContext() instanceof IEvaluationContext) {
			IEvaluationContext appContext = (IEvaluationContext) event
					.getApplicationContext();
			window = (IWorkbenchWindow) appContext
					.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_NAME);
			if (window == null) {
				throw new ExecutionException("No active workbench window"); //$NON-NLS-1$
			}
			part = (IWorkbenchPart) appContext
					.getVariable(ISources.ACTIVE_PART_NAME);
			if (part == null) {
				throw new ExecutionException("No active part"); //$NON-NLS-1$
			}
		}

		if (part instanceof IEditorPart) {
			window.getActivePage().closeEditor((IEditorPart) part, true);
		} else if (part instanceof IViewPart) {
			window.getActivePage().hideView((IViewPart) part);
		}

		return null;
	}
}

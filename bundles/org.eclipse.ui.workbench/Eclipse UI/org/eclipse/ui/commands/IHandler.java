/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.ui.commands;

import java.util.Set;

/**
 * An instance of this interface is an handler as defined by the extension
 * point <code>org.eclipse.ui.commands</code>.
 * <p>
 * This interface is not intended to be extended by clients.
 * </p>
 * 
 * @since 3.0
 * @see IHandler#getHandler
 */
public interface IHandler {

    /**
     * Registers an instance of <code>IHandlerListener</code> to listen for
     * changes to properties of this instance.
     * 
     * @param handlerListener
     *            the instance to register. Must not be <code>null</code>.
     *            If an attempt is made to register an instance which is
     *            already registered with this instance, no operation is
     *            performed.
     */
    void addHandlerListener(IHandlerListener handlerListener);

    /**
     * Executes with the specified parameter.
     * 
     * @param parameter
     *            the parameter.
     * @throws ExecutionException
     *             if an exception occurred during execution.
     */
    void execute(Object parameter) throws ExecutionException;

    /**
     * Returns an attribute value given an attribute name.
     * 
     * @param attributeName
     *            the name of the attribute. Must not be <code>null</code>.
     * @return the value of the attribute.
     * @throws NoSuchAttributeException
     *             if the attribute is not defined.
     */
    Object getAttributeValue(String attributeName)
            throws NoSuchAttributeException;

    /**
     * Returns the set of names of defined attributes.
     * <p>
     * Notification is sent to all registered listeners if this property
     * changes.
     * </p>
     * 
     * @return the set of names of defined attributes. This set may be empty,
     *         but is guaranteed not to be <code>null</code>. If this set is
     *         not empty, it is guaranteed to only contain instances of <code>String</code>.
     */
    Set getDefinedAttributeNames();

    /**
     * Unregisters an instance of <code>IPropertyListener</code> listening
     * for changes to properties of this instance.
     * 
     * @param handlerListener
     *            the instance to unregister. Must not be <code>null</code>.
     *            If an attempt is made to unregister an instance which is not
     *            already registered with this instance, no operation is
     *            performed.
     */
    void removeHandlerListener(IHandlerListener handlerListener);
}

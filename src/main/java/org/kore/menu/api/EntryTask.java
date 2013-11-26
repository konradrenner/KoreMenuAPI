/*
 * Copyright (C) 2013 Konrad Renner.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.kore.menu.api;

import org.kore.menu.api.security.SecurityContext;
import org.kore.menu.api.security.SecurityInspector;

/**
 * Represtens a task which is mapped to an entry. A task can be e.g. 'print' or
 * the starting point for a faces flow
 *
 * @author Konrad Renner
 */
public interface EntryTask extends Entry {

    /**
     * Executes the Task, throws an SecurityExecption if the task can not be
     * started because of security issues
     *
     * @param context
     * @param inspector
     * @throws SecurityException
     */
    void execute(SecurityContext context, SecurityInspector inspector) throws SecurityException;

    /**
     * Executes a mapped sub-task, return true if everthing is OK (task started
     * and if the task runs not asynchron, true if the task was completed)
     *
     * @param taskid
     * @param context
     * @param inspector
     * @return boolean
     */
    @Override
    boolean executeTask(EntryUID taskid, SecurityContext context, SecurityInspector inspector);
}

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

import java.util.Map;
import java.util.Set;
import org.kore.menu.api.security.Authorization;
import org.kore.menu.api.security.SecurityContext;
import org.kore.menu.api.security.SecurityInspector;
import org.kore.menu.api.security.SecurityUID;

/**
 *
 * @author Konrad Renner
 */
public class DefaultEntryGroup implements EntryGroup {

    final EntryUID uid;
    final Map<EntryUID, Entry> entries;
    final Map<SecurityUID, Authorization> auths;
    final Entry parent;
    final Map<EntryUID, EntryTask> tasks;

    //Construct with builder
    private DefaultEntryGroup(EntryUID uid, Map<EntryUID, Entry> entries, Map<SecurityUID, Authorization> auths, Entry parent, Map<EntryUID, EntryTask> tasks) {
        this.uid = uid;
        this.entries = entries;
        this.auths = auths;
        this.parent = parent;
        this.tasks = tasks;
    }



    @Override
    public boolean isMainGroup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry> getEntries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entry getEntry(EntryUID uid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntryUID getUID() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Authorization> getRequiredAuthorization() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDisplayable(SecurityContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean controlAuthorizations(SecurityInspector inspector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entry getParent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntryGroup getChildren() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<EntryTask> getMappedTasks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean executeTask(EntryUID taskid, SecurityContext context, SecurityInspector inspector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class Builder {
        //TODO
    }
}

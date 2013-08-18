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

/**
 *
 * @author Konrad Renner
 */
public class EntryUIDImpl implements EntryUID {

    final String id;

    EntryUIDImpl(String id) {
        this.id = id;
    }

    @Override
    public Namespace getNamespace() {
        return new Namespace() {
            @Override
            public String getName() {
                return "";
            }
        };
    }

    @Override
    public String getIdentifierString() {
        return id;
    }

    @Override
    public String getSortingKey() {
        return id;
    }

    @Override
    public int compareTo(EntryUID o) {
        if (this.equals(o)) {
            return 0;
        }
        return getSortingKey().compareTo(o.getSortingKey());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntryUIDImpl other = (EntryUIDImpl) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }
}

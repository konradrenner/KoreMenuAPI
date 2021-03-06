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
package org.kore.menu.api.security;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents a single authorization
 *
 * @author Konrad Renner
 */
public interface Authorization extends Serializable {

    SecurityUID getUID();

    /**
     * Returns a set of SecurityContexts which are mapped to the authorization.
     * This means, that e.g. an Entry which is mapped to a SecurityContext
     * 'READ' is only displyable in this context
     *
     * @return Set<SecurityContext>
     */
    Set<SecurityContext> getConnectedContexts();
}

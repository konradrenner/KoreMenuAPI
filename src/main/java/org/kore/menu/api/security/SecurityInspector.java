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
import org.kore.menu.api.Entry;

/**
 * Inspects an entry if required authorizations are available. The
 * SecurityInspector should be initialized with the current user, so he can decide if the required authoriziations for the actual
 * context and user are there.
 *
 * @author Konrad Renner
 */
public interface SecurityInspector extends Serializable {

    /**
     * Checks if the user (the SecurityInspector should be intialized with an
     * user) has the required authorizations for the given context. If not, a SecurityException is thrown
     *
     * @param context
     * @param entry
     * @throws SecurityException
     */
    void inspect(SecurityContext context, Entry entry) throws SecurityException;

    /**
     * Returns all occured security violations during inspection
     *
     * @return Set<SecurityViolation>
     */
    Set<SecurityViolation> getViolations();
}

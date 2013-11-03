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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Konrad Renner
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultEntryGroupTest {

    DefaultEntryGroup group;
    EntryUID groupID = new EntryUIDImpl("mainGroup");
    @Mock
    Entry entry1;
    EntryUID entry1ID = new EntryUIDImpl("entry1");
    @Mock
    Entry entry2;
    EntryUID entry2ID = new EntryUIDImpl("entry2");
    @Mock
    Entry entry3;
    EntryUID entry3ID = new EntryUIDImpl("entry3");
    @Mock
    Entry entry4;
    EntryUID entry4ID = new EntryUIDImpl("entry4");
    @Mock
    Entry entry5;
    EntryUID entry5ID = new EntryUIDImpl("entry5");
    @Mock
    Entry entry6;
    EntryUID entry6ID = new EntryUIDImpl("entry6");
    @Mock
    Entry entry7;
    EntryUID entry7ID = new EntryUIDImpl("entry7");
    @Mock
    EntryTask task;
    EntryUID taskID = new EntryUIDImpl("task");

    public DefaultEntryGroupTest() {
    }

    @Before
    public void setUp() {



        group = new DefaultEntryGroup.Builder(groupID, createMockData()).build();
    }

    Map<EntryUID, Entry> createMockData() {
        when(entry1.getUID()).thenReturn(entry1ID);
        when(entry2.getUID()).thenReturn(entry2ID);
        when(entry3.getUID()).thenReturn(entry3ID);
        when(entry4.getUID()).thenReturn(entry4ID);
        when(entry5.getUID()).thenReturn(entry5ID);
        HashSet<EntryTask> tasks = new HashSet<EntryTask>();
        tasks.add(task);
        when(task.getUID()).thenReturn(taskID);
        when(entry5.getMappedTasks()).thenReturn(tasks);
        when(entry6.getUID()).thenReturn(entry6ID);
        when(entry7.getUID()).thenReturn(entry7ID);

        HashMap<EntryUID, Entry> subgroup1 = new HashMap<EntryUID, Entry>();
        subgroup1.put(entry2ID, entry2);
        subgroup1.put(entry3ID, entry3);
        DefaultEntryGroup sub1 = new DefaultEntryGroup.Builder(new EntryUIDImpl("sub1"), subgroup1).addParent(groupID).build();

        HashMap<EntryUID, Entry> subgroup2 = new HashMap<EntryUID, Entry>();
        subgroup2.put(entry6ID, entry6);
        subgroup2.put(entry7ID, entry7);
        DefaultEntryGroup sub2 = new DefaultEntryGroup.Builder(new EntryUIDImpl("sub2"), subgroup2).addParent(groupID).build();

        HashMap<EntryUID, Entry> subgroup3 = new HashMap<EntryUID, Entry>();
        subgroup3.put(entry5ID, entry5);
        DefaultEntryGroup sub3 = new DefaultEntryGroup.Builder(new EntryUIDImpl("sub3"), subgroup3).addParent(sub2.getUID()).build();
        subgroup2.put(sub3.getUID(), sub3);

        HashMap<EntryUID, Entry> ret = new HashMap<EntryUID, Entry>();
        ret.put(entry1ID, entry1);
        ret.put(sub1.getUID(), sub1);
        ret.put(entry4ID, entry4);
        ret.put(sub2.getUID(), sub2);


        return ret;
    }

    @Test
    public void testGetType() {
        assertEquals(Entry.Type.GROUP, group.getType());
    }

    @Test
    public void testGetEntries() {
//        assertThat(createMockData().values(), CoreMatchers.);
    }

    @Test
    public void testGetEntry() {
    }

    @Test
    public void testSearchForEntryDeeply_EntryUID() {
    }

    @Test
    public void testSearchForEntryDeeply_EntryUID_EntryGroup() {
    }

    @Test
    public void testGetAllofType() {
    }

    @Test
    public void testSearchGroupForTypes() {
    }

    @Test
    public void testGetUID() {
        assertEquals(groupID, group.getUID());
    }

    @Test
    public void testGetChildren() {
    }

    @Test
    public void testGetMappedTasks() {
    }

    @Test
    public void testGetMappedTasksNoFound() {
    }

}
/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.history.ModelJUnit;

import com.intellij.history.core.InMemoryLocalHistoryFacade;
import com.intellij.history.core.LocalHistoryFacade;
import com.intellij.history.core.LocalHistoryTestCase;
import com.intellij.history.core.StoredContent;
import com.intellij.history.core.changes.*;
import com.intellij.history.core.tree.DirectoryEntry;
import com.intellij.history.core.tree.FileEntry;
import com.intellij.history.core.tree.RootEntry;

/**
 * Created by johannes on 2016-05-16.
 */
public class Adapter extends LocalHistoryTestCase {
  

  public void CreateFileChange(){
    new CreateFileChange(nextId(), "file");
  }

  public void CreateFolderChange(){
    new CreateDirectoryChange(nextId(), "dir");
  }

  public void CreateMoveChange(){
    new MoveChange(nextId(), "dir2/file", "dir1");
  }

  public void CreateRenameChange(){
    new RenameChange(nextId(), "new name", "old name");
  }
  public void CreateDeleteChange(){
    DirectoryEntry dir = new DirectoryEntry("dir");
    dir.addChild(new FileEntry("file", new StoredContent(333), -1, false));
    dir.addChild(new DirectoryEntry("subDir"));
    new DeleteChange(nextId(), "entry", dir);

  }
  public void CreateChangeContentChange(){
    new ContentChange(nextId(), "file", null, -1);
  }

}

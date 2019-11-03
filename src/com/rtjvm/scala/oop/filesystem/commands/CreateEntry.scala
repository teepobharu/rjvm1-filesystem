package com.rtjvm.scala.oop.filesystem.commands

import com.rtjvm.scala.oop.filesystem.files.{DirEntry, Directory}
import com.rtjvm.scala.oop.filesystem.filesystem.State

abstract class CreateEntry(name: String) extends Command {
  override def apply(state: State): State = {
    val wd = state.wd
    if(wd.hasEntry(name)){
      state.setMessage(("Entry :" + name + " already exists!" ))
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + " must not contain seperator!")
    } else if (checkIllegal(name)) {
      state.setMessage(name + ": Illegal entry name!")
    } else {
      doCreateEntry(state, name)
    }
  }
  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }
  def doCreateEntry(state: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {

        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
        /*
            /a/b
            {contents}
            {new entry} /e
            updateStructure( root, [a,b] , /e )
              => path.isEmpty?
              => oldEntry = /a
              root.replaceEntry(a , updateStructure(/a, [b], /e)
                 => path.isEmpty>
                 => oldEntry = /b
                 => /a.replaceEntry(b , updateStructure(/b, [], /e)
         */
      }
    }

    /*
      someDir
      /a
      /b
      {new} /d
      => new someDir
        /a
        /b
        /d

     */
    val wd = state.wd
    val fullPath = wd.path

    //1. all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // 2. create new directory entry in the wd
    // TODO implement this
    //    val newDir: Directory = Directory.empty(wd.path, name)
    val newDir: DirEntry = createSpecificEntry(state)

    // 3. update the whole dir structure starting from the root
    // (the directory structure is IMMUTABLE)
    val newRoot = updateStructure(state.root, allDirsInPath, newDir)

    // 4. find new working dir INSTANCE given wd's full path, in the NEW directory structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }
  def createSpecificEntry(state: State): DirEntry
}

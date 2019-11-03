package com.rtjvm.scala.oop.filesystem.commands
import com.rtjvm.scala.oop.filesystem.files.{DirEntry, Directory}
import com.rtjvm.scala.oop.filesystem.filesystem.State

class Mkdir(name: String) extends CreateEntry(name){
  override def createSpecificEntry(state: State): DirEntry =
    Directory.empty(state.wd.path, name)
}

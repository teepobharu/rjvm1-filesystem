package com.rtjvm.scala.oop.filesystem.commands

import com.rtjvm.scala.oop.filesystem.files.{DirEntry, File}
import com.rtjvm.scala.oop.filesystem.filesystem.State

class Touch(name:String) extends CreateEntry(name){

  override def createSpecificEntry(state: State):DirEntry =
    File.empty(state.wd.path,name)
}

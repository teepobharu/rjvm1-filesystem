package com.rtjvm.scala.oop.filesystem.files

import com.rtjvm.scala.oop.filesystem.filesystem.FilesystemException

class File (override val parentPath: String, override val name: String, contents : String)
  extends DirEntry(parentPath , name){
  def asDirectory: Directory =
    throw new FilesystemException("A file cannot be converted to a directory!")

  def getType: String = "file"

  def isDirectory: Boolean = false
  def asFile: File = this
  def isFile: Boolean = true
  def setContents(newContents: String): File =
    new File(parentPath, name, newContents)
  def appendContents(newContents: String): File =
    setContents(contents + "\n" + newContents)
}

object File{

  def empty(parentPath: String, name: String): File =
    new File(parentPath, name, "")

}

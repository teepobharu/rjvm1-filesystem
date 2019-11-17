package com.rtjvm.scala.oop.filesystem.files

abstract class DirEntry(val parentPath:String, val name: String) {
  def path: String = {
    val separatorIfNecessary =
      if (Directory.ROOT_PATH.equals(parentPath)) ""
    else Directory.SEPARATOR

    parentPath + separatorIfNecessary + name
  }
  def asDirectory: Directory
  def isDirectory: Boolean
  def isFile: Boolean
  def getType: String
}

package com.rtjvm.scala.oop.filesystem.commands

import com.rtjvm.scala.oop.filesystem.filesystem.State

class Pwd extends Command{
  override def apply(state: State):State =
    state.setMessage(state.wd.path)

}

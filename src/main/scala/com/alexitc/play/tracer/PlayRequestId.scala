package com.alexitc.play.tracer

import java.util.UUID

/**
 * Represents a unique identifier for a Play! request.
 *
 * @param self the unique identifier
 */
class PlayRequestId(val self: String) {

  override def toString: String = s"PlayRequest($self)"
}

object PlayRequestId {

  def random: PlayRequestId = {
    val id = UUID
        .randomUUID()
        .toString
        .replace("-", "")

    new PlayRequestId(id)
  }
}

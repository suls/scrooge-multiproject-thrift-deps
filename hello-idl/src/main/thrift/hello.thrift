#@namespace scala hello.idl

include "world.thrift"

struct Hello {
  1: optional world.World world;
}

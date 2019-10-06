# Netty-UDP

### 利用Netty UDP实现双向通信

基于Netty-TCP的连接中，设计好协议的类型，编写好相应的编解码器，
针对不同类型的数据包，实现对应的ChannelHandler，然后将其加入到Pipeline中即可，方便扩展。

基于Netty-UDP的连接中（准确说不能叫连接，因为UDP协议是不可靠的、无连接的传输协议），因为UDP传输的数据包只有
Datagram一种数据包，所以为了能够像TCP一样，利用统一的编解码器，针对不同类型包的数据，方便客户端和服务端的扩展。

本项目将传统Netty-TCP形式的代码改为Netty-UDP，从而实现双向通信

TCP和UDP数据包的格式：
> TCP Packet:

|指令|数据长度|数据|
|:----:|:----:|----:|
|1 Byte|4 Byte|N Byte|

> UDP Datagram

|ByteBuf|remote address|
|:----:|:----:|

编码：是将各种类型的Packet编码（跟TCP相同），然后将其写入到ByteBuf
解码：从Datagram中取出content(ByteBuf)，然后解码过程和TCP解码过程相同




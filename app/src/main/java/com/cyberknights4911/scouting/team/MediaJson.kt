package com.cyberknights4911.scouting.team

import com.squareup.moshi.JsonClass
/*

  {
    "details": {
      "base64Image": "iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAABGdBTUEAALGPC/xhBQAAABl0RVh0U29mdHdhcmUAQWRvYmUgSW1hZ2VSZWFkeXHJZTwAAAU1SURBVFhHvVnfT5tVGOZP2J/AjSnGmHBDvDAxi/oHcK1hw0XHdCbCrpbsZglmMcZEo9nNvCCYRSWLkLipIIwiiIyGrZKyUX60XbuuCG1X7Oy6lpbj+3x9z+k5X792/QVP8oR+5zvnPU/POe+PUzqOE0KIE8RBopeoI0S8TDzBXY8fNDmEpYi1AOHHK5Im7CS6Mbsd/4bXRT6T5icFNw89etBkvURj1TLxqPBeuyh+7nMprl2/wm8VTrKJowNN0l+aqwwI0YVJ/nLaJTYnvuFeFibYzNGAJhgpzVMCVm3uUq8havqMS8yf7RLLH5W4+EkP97aQYlPtBxk3xCXWl8VvAz1K2GS/S/x1rixMEm34IhJsrr0gu4a4yPy4sWqz71cKk7zzYZflNBJssn0gmzXFzX3gLExyYaCLR5bAZtsDsteSOHDt6nkebcHLplsHGWtZHLY3SWdVw2U23xrIELKDgi7u5inTS2vRtnpAJ0/RPMiIEefs4hYdPNWJd4Z67Nmk9dUjI8gQCk2Lq9xaFA2t5WIy0E1U6QuhQca5RsSBkelRtqLQWoojAyiXHMWBjYjzj1xkKwqDPE3zICOqjsO5mRl6U4mrx1slfZVOMcJTNA8y8mXJVkmcnltrZQg7vZ/22p2i9ZhHRgyn0MulqfdcjkKc6CSO2LJTGOdO91iUSfBEJzF2Lg1WhBPYbL16JiNqa1Ft6E6B/Okkxk7PhR6R1goBAsR18xTNg4ygXFdYvNKnxKGecxJjp4M4APEO14AJIi5LCPqNZw8ebAF1nRSHeFfP1qJPdGGcLdQFCEf6rG/reYAFz1fnlcB6vBbiYosNidOBeWsfAeoA57CAwy3FgU4VsU5dXD6fF6lUqrC3u5sNBoNPifuS4XD4GdrT6bQoFApWfw21zym9PGl1I+jb+6KwgjO3d29GZLNZEQmHc5FIJJZMJDYymcwamVqxE+14HwgE4tFoNGsTWv0KSi9V7NMFumtsr3QIrBpNmMrlcqs03BCUunvvwd5t9wb+2t/RSj4IhUI5+qzAckxQO7xXxT5dYLV8iyAsLz20KiKZTAbp40p45LvQyrunU7df6S5Mdb4k7Jx77fXc3+c+TkA0+u/s7AQwXoIlmaB2I+fqac0uDOdt60fz4o1t2vD7s8sDAxknUdW4Ojz8hFbwOZuxwJLKoDZVJdvF6QIhbPWLU04xzkI+l8ts//rD/tLnw+KPvnfE7Ftvi99fftUQhGe0z589Izxff1YMTf+UpC93wCYA8wJPDUaVrP8SgAyCNPfP3RnLCfQ7rAYVlgiFg6Wb69m5G1vJqe8fRSbH4uFb1xP+yRv/SeIZ7cmZsUjePbZx4J3FNuso14f0YIiLb3rE1OAbljiUVfqd1QE4r1h5FLMKBd+fW5i4XhYf3te/YHl76XOFuOVvLwjPtSERmBy1J3kdEGb8rme1MorRLWtl6uVhIrbHQ4GQNGiUUlLcyugl8eShj1sVIAhfBjHSMYhSu3Kww/34rpOQahQH+SQPBUrnjz6oZZXiwHRsm1sVMPELKxDqo9+TU05CnIjzSv3L8UXe7vhBPH+aVCsHoTbUfV+gvqrAIBSKO6HN/MK430mUJMQdJh4/4jESJQfhB1HIPRPrt66KTOIxtyj0Wx3rBPU3SjRGirY7Bicohu5vF4I+H/7iGe30Xl85AEepdK7pg+E5NjQkToLGqSK3SZTnpQdVFNjQlDgJGo+tVumyTqB/L5sogxqxLTjcqHLxty2/EZMd+W8HVM7VxGIH8R7RwVaodnT8D/+Fu/IoknnTAAAAAElFTkSuQmCC"
    },
    "direct_url": "",
    "foreign_key": "avatar_2023_frc254",
    "preferred": false,
    "type": "avatar",
    "view_url": ""
  },
  {
    "details": {},
    "direct_url": "https://i.imgur.com/8tYYbRzh.jpg",
    "foreign_key": "8tYYbRz",
    "preferred": false,
    "type": "imgur",
    "view_url": "https://imgur.com/8tYYbRz"
  },
 */
@JsonClass(generateAdapter = true)
data class MediaJson(
    val details: Details,
    val direct_url: String,
    val foreign_key: String,
    val preferred: Boolean,
    val type: String,
    val view_url: String
)

@JsonClass(generateAdapter = true)
data class Details(
    val base64Image: String = ""
)

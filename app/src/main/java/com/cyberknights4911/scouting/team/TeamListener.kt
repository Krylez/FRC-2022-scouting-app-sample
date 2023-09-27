package com.cyberknights4911.scouting.team

class TeamListener(val clickListener: (teamWithLogo: TeamWithLogo) -> Unit) {
    fun onClick(teamWithLogo: TeamWithLogo) = clickListener(teamWithLogo)
}

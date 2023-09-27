package com.cyberknights4911.scouting.match

class MatchListener(val clickListener: (matchWithTeams: MatchWithTeams) -> Unit) {
    fun onClick(matchWithTeams: MatchWithTeams) = clickListener(matchWithTeams)
}

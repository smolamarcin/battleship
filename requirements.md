| Date         | Requirement    | 
| ------------- |:-------------:| 
|dd.mm.rrrr|It must work (it it doesn’t, it’s disqualified): if I cannot play, it doesn't work.|
|dd.mm.rrrr|60% unit test code coverage (lines).|
|dd.mm.rrrr|Functions in accordance with functional requirements.|
|dd.mm.rrrr|Code quality – non-OO code is tolerated in little amounts.|
|dd.mm.rrrr|Project mantra followed (Git, Maven, test cases, etc.).|
|dd.mm.rrrr|Java FX simple GUI|
|dd.mm.rrrr|Network game, client-server architecture|
|dd.mm.rrrr|Both players are human players|
|dd.mm.rrrr|One game only|
|dd.mm.rrrr|10x10 board|
|dd.mm.rrrr|Fleet consists of: 4-mast ship, 2 3-mast ships, 3 2-mast ships and 4 1-mast ships.|
|dd.mm.rrrr|Winner has ships remaining while loser has none.|
|dd.mm.rrrr|Game messages have configurable target: console (System.out, System.err) or logs or external printer.|
|dd.mm.rrrr|We are bi-lingual: Polish and English are fine. In future we want to add more languages: messages are to be read from a file for chosen language. Choosing the language depends on configuration variable.|
|dd.mm.rrrr|Drawing the boards for a player (fleet board has player's fleet and where opponent shot, "seen" board has where player fired and what he has shot).|
|dd.mm.rrrr|Placing the fleet - diagonal placing is disallowed, only horizontal and vertical. Humans can place ships but they can also choose to randomize placement. Ships cannot touch (no adjacent field to a ship can have a ship). Ships can be partially vertical and partially horizontal, if they have the length.|
|dd.mm.rrrr|Firing the shot - choose a place, shoot. If you hit, you repeat the shot. You can repeat as many times as you hit.|
|dd.mm.rrrr|Hitting the ship - hit happens when place chosen has enemy ship. Mark this part of ship as hit, ask for another shot. One can repeat the shot into already hit (or even sunken) ship, but this doesn't give the right to another shot.|
|dd.mm.rrrr|Missing the ship - misses are marked on "seen" board. One can shoot twice in the same place if it's a miss.|
|dd.mm.rrrr|Sinking the ship - if all masts of a ship are hit, ship sinks. Once the ship has sunk, mark all adjacent fields as "missed", since none of them can have a ship anyway.|
|dd.mm.rrrr|Sinking last ship, that is, winning.|
|dd.mm.rrrr|Nuke - thrice per game player chooses a 3x3 area and "nukes" it, that is, all ships within take damage as if shot. This is done in addition to normal shot. Only 4-mast ship has nukes, so once they are sunk, nukes cannot be used.|
|dd.mm.rrrr|Holy master - everything on master is holy, this is what is being checked by customer|
|dd.mm.rrrr|CI server - before anything gets pulled into master, it is integrated with master by CI server, it runs tests, checks, etc.|
|dd.mm.rrrr|Reviewers - pull-requests to master that are handled by CI server are then reviewed internally by teammates|
|dd.mm.rrrr|Outside reviewers - once team says yes, outsiders come in (each team chooses external reviewers). Two external reviewers need to say "code is OK" before it can be pulled to master.|
|dd.mm.rrrr|All code is on GitHub|
|dd.mm.rrrr|Jenkins (or equivalent) is used as CI server|
|dd.mm.rrrr|Maven is used by Jenkins and team|
|dd.mm.rrrr|Maven has Findbugs, JaCoCo and Checkstyle integration|
|dd.mm.rrrr|Dependency convergence must be 100%, verified with maven-enforcer plugin|
|dd.mm.rrrr|All dependencies that are NOT newest are recorded (along with reason why) in the dependencies|
|dd.mm.rrrr|Dependencies are newest or reason for why is in the docs, versions are kept up to date with Maven versions plugin.|
|dd.mm.rrrr|Sonar is used to keep quality gates (just internally is fine).|
|dd.mm.rrrr|Acceptance tests are welcome, one per feature is required|
|dd.mm.rrrr|Documentation should be provided, explaining program architecture (diagram is necessary, CRC diagrams are most welcome)|

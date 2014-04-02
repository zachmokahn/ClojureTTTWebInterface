(ns WebInterface.constants)

(def GAME       "game")
(def NEW-GAME   "new-game")
(def MAKE-MOVE  "make-move")
(def MODE       "gameMode")
(def DIFFICULTY "gameDifficulty")
(def HARD       "hard")
(def BOARD      "gameBoard")
(def TURN       "gameTurn")
(def MOVE       "gameMove")
(def PLAYER1    "first-player")
(def PLAYER2    "second-player")
(def DRAW       "draw")
(def ROOT       "public")
(def INDEX      "index.html")
(def INVALID    "404.html")
(def PVC        "singlePlayer")

(def game-modes
  { :pvc {:player1 :player
          :player2 :computer}
    :pvp {:player1 :player
          :player2 :player}})

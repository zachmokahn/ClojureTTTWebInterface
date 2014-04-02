(ns WebInterface.parser-spec
  (:require [speclj.core :refer :all]
            [WebInterface.parser :refer :all]
            [clojure.data.json :as json]))
(def _new-game
  (java.util.Hashtable. {"gameTurn" "first-player",
                         "gameMode" "singlePlayer",
                         "gameDifficulty" "hard"}))

(def _parsed-new-game
  {:turn :player1,
   :mode :pvc
   :difficulty :hard})

(def board (json/write-str ["-" "-" "-" "-" "-" "-" "-" "-" "-"]))

(def _active-game
  (java.util.Hashtable. {"gameTurn" "first-player",
                         "gameMode" "singlePlayer",
                         "gameDifficulty" "hard",
                         "gameMove" "4",
                         "gameBoard" board }))

(def _parsed-active-game
  {:turn :player1,
   :mode :pvc,
   :move "4",
   :board ["-" "-" "-" "-" "-" "-" "-" "-" "-"],
   :difficulty :hard})

(describe "Parser Spec:"
  (it "parses a new game properly"
  (should= _parsed-new-game
           (parse-new-game _new-game)))

  (it "parses an active game properly"
  (should= _parsed-active-game
           (parse-active-game _active-game))))

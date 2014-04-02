(ns WebInterface.interface-spec
  (:require [speclj.core :refer :all]
            [WebInterface.core :refer :all]))

(def _turn :player2 )
(def _mode :pvc)
(def _empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])
(def _fake-parsed-params { :board _empty-board :mode _mode :turn _turn } )

(describe "Core Spec:"

  (describe "#determine-winner"
    (with-redefs [ttt.rules/draw? (fn [_] true)]
      (it "returns 'draw' if the game is a draw"
        (should= WebInterface.constants/DRAW
                 (determine-winner "data"))))

    (it "returns 'player1' if they won"
      (with-redefs [ttt.rules/draw?  (fn [_] false)
                    ttt.rules/win? (fn [_ _] true)]
        (should= WebInterface.constants/PLAYER1
                 (determine-winner "data"))))

    (it "returns 'draw' if the game is a draw"
        (should= WebInterface.constants/PLAYER2
      (with-redefs [ttt.rules/draw?  (fn [_] false)
                    ttt.rules/win? (fn [_ _] false)]
                 (determine-winner "data")))))

  (describe "#return-data"
    (it "returns the provided  data in a map"
      (let [_fake-return-data {:board "board" :turn "turn"}]
      (should= _fake-return-data
               (return-data "board" "turn")))))

  (describe "#get-players"
    (it "returns propper players types for ':pvp'"
      (should= :player (:player1 (get-players :pvp)))
      (should= :player (:player2 (get-players :pvp))))

    (it "returns propper players types for ':pvc'"
      (should= :player   (:player1 (get-players :pvc)))
      (should= :computer (:player2 (get-players :pvc)))))

  (describe "#game-evaluation"
    (it "returns game over if game is over?"
      (with-redefs [ttt.rules/game-over? (fn [_] true)]
      (should-invoke game-over-return {:with ["game-data"]}
                     (game-evaluation "game-data"))))

    (it "returns game data if game not over"
      (with-redefs [ttt.rules/game-over? (fn [_] false)]
      (should-invoke game-active-return {:with ["data"]}
                     (game-evaluation "data")))))

  (describe "#start-game"
    (it "calls the computer move if computer player is first player"
      (should-invoke play-game {:with [_fake-parsed-params]}
                     (start-game _fake-parsed-params)))))

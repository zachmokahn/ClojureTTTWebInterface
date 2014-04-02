(ns WebInterface.core
  (:require [WebInterface.constants :refer :all]
            [ttt.rules :refer :all]
            [ttt.board :refer :all]
            [ttt.ai :refer :all]))

(declare play-game move-game)

(defn determine-winner [game-data]
  (let [board (:board game-data)]
    (if (draw? board) DRAW
    (if (win? board :player1) PLAYER1 PLAYER2))))

(defn return-data [board turn]
  {:board board :turn turn})

(defn game-over-return [game-data]
  (assoc game-data :game-over true
   :winner (determine-winner game-data)))

(defn game-active-return [game-data]
  (assoc game-data :game-over false))

(defn get-players [mode]
  (mode game-modes))

(defn computer? [player mode]
  (= :computer (player (get-players mode))))

(defn game-evaluation [data]
  (if (game-over? (:board data))
    (game-over-return data)
    (game-active-return data)))

(defn play-game [game-parameters]
  (game-evaluation (move-game game-parameters)))

(defn computer-move [board turn difficulty]
  (if (= difficulty :hard)
    (move board (best-move board turn) turn)
    (move board (random-move board turn) turn)))

(defn take-turn [turn board index mode difficulty]
  (if (computer? turn mode)
    (computer-move board turn difficulty)
    (move board (read-string index) turn)))

(defn move-game [game-parameters]
  (let [board       (:board       game-parameters)
        mode        (:mode        game-parameters)
        turn        (:turn        game-parameters)
        index       (:move        game-parameters)
        difficulty  (:difficulty  game-parameters)
        opponent  (change-turn turn)
        next-board (take-turn turn board index mode difficulty)]
        (if (and (computer? opponent mode)
                 (not (game-over? next-board)))
          (return-data (computer-move next-board opponent difficulty) turn)
          (return-data next-board opponent))))

(defn start-game [game-parameters]
  (let [board new-board
        turn  (:turn  game-parameters)
        mode  (:mode  game-parameters)]
    (if (computer? turn mode)
      (play-game (assoc game-parameters :board board))
      (return-data board turn))))

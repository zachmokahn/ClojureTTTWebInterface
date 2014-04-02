(ns WebInterface.TTTService
  (:require [WebInterface.core :refer [start-game play-game]]
            [WebInterface.parser :refer [parse-new-game parse-active-game]]
            [clojure.data.json :as json])
  (:gen-class
    :methods [#^{:static true} [start [java.util.Hashtable] String]
              #^{:static true} [play  [java.util.Hashtable] String]]))

(defn convert-to-hashtable [coll]
  (let [hashtable (new java.util.Hashtable)]
    (hashtable)))

(defn -start [game-parameters]
  (let [parameters (parse-new-game game-parameters)
        game-data (start-game parameters)]
    (json/write-str game-data)))

(defn -play [game-parameters]
  (let [parameters (parse-active-game game-parameters)
        game-data (play-game parameters)]
    (json/write-str game-data)))

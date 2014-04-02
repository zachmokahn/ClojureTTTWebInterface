(defproject webinterface "0.1.0"
  :description "Interface to Talk to Server"
  :url "http://www.github.com/zachmokahn/WebInterfaceCTT"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/data.json "0.2.4"]
                 [ttt/ttt "1.0.0"]]
  :profiles {:dev {:dependencies [[speclj "3.0.1"]]}}
  :plugins [[speclj "3.0.1"]]
  :aot [WebInterface.TTTService]
  :main WebInterface.TTTService
  :test-paths ["spec"])

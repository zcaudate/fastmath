(ns fastmath.fields-examples
  (:require [metadoc.examples :refer :all]
            [fastmath.fields :refer :all]
            [fastmath.vector :as v]
            [fastmath.core :as m]))

(add-examples composition
  (example "Usage" (let [field-1 (field :sinusoidal)
                         field-2 (field :swirl)
                         field-comp (composition field-1 field-2)]
                     (field-comp (v/vec2 0.5 0.3)))))

(add-examples sum
  (example "Usage" (let [field-1 (field :sinusoidal)
                         field-2 (field :swirl)
                         field-sum (sum field-1 field-2)]
                     (field-sum (v/vec2 0.5 0.3)))))

(add-examples multiplication
  (example "Usage" (let [field-1 (field :sinusoidal)
                         field-2 (field :swirl)
                         field-multiplication (multiplication field-1 field-2)]
                     (field-multiplication (v/vec2 0.5 0.3)))))

(add-examples derivative
  (example "Usage" (let [f (derivative (field :sinusoidal) 1.0e-8)]
                     (f (v/vec2 0.0 0.0)))))

(add-examples grad-x
  (example "Usage" (let [f (grad-x (field :sinusoidal) 1.0e-8)]
                     (f (v/vec2 0.0 0.0)))))

(add-examples grad-y
  (example "Usage" (let [f (grad-y (field :sinusoidal) 1.0e-8)]
                     (f (v/vec2 0.0 0.0)))))

(add-examples jacobian
  (example "Usage" (let [f (jacobian (field :sinusoidal) 1.0e-8)]
                     (f (v/vec2 0.0 0.0)))))

(add-examples divergence
  (example "Usage" (let [f (divergence (field :sinusoidal) 1.0e-8)]
                     (f (v/vec2 0.0 0.0)))))

(add-examples curl
  (example "Usage" (let [f (curl (field :swirl) 1.0e-8)]
                     (f (v/vec2 0.0 0.0)))))

(add-examples magnitude
  (example "Usage" (let [f (magnitude (field :sinusoidal))]
                     (f (v/vec2 m/HALF_PI m/HALF_PI)))))

(add-examples heading
  (example "Usage" (let [f (heading (field :sinusoidal))]
                     (m/degrees (f (v/vec2 m/HALF_PI m/HALF_PI))))))

(add-examples cross
  (example "Usage" (let [f (cross (field :swirl))]
                     (f (v/vec2 1 1))))
  (example "Usage (two fields)" (let [f (cross (field :sinusoidal) (field :swirl))]
                                  (f (v/vec2 1 1)))))


(add-examples dot
  (example "Usage" (let [f (dot (field :swirl))]
                     (f (v/vec2 1 1))))
  (example "Usage (two fields)" (let [f (dot (field :sinusoidal) (field :swirl))]
                                  (f (v/vec2 1 1)))))

(add-examples angle-between
  (example "Usage" (let [f (angle-between (field :swirl))]
                     (f (v/vec2 1 1))))
  (example "Usage (two fields)" (let [f (angle-between (field :sinusoidal) (field :swirl))]
                                  (f (v/vec2 1 1)))))


(add-examples scalar->vector-field
  (example "Usage" (let [f (scalar->vector-field v/heading (field :sinusoidal))]
                     (v/applyf (f (v/vec2 m/HALF_PI m/HALF_PI)) m/degrees)))
  (example "Usage (two fields)" (let [f (scalar->vector-field v/heading (field :sinusoidal) (field :julia))]
                                  (v/applyf (f (v/vec2 m/HALF_PI m/HALF_PI)) m/degrees))))

(add-examples parametrization
  (example "Get random parametrization for given field" (parametrization :auger))
  (example "Add lacking fields" (parametrization :auger {:scale 1.0 :freq 1.0}))
  (example "Returns empty map when field doesn't have parametrization" (parametrization :sinusoidal)))

(add-examples field
  (example-session "Get vector field by name" (field :sinusoidal) ((field :sinusoidal) (v/vec2 m/HALF_PI m/HALF_PI)))
  (example-session "Get vector field by name and scale" (field :sinusoidal 0.5) ((field :sinusoidal 0.5) (v/vec2 m/HALF_PI m/HALF_PI)))
  (example "Apply parametrization" (let [params (parametrization :cpow3)
                                         f (field :cpow3 1.0 params)]
                                     {:parametrization params
                                      :value (f (v/vec2 -1.0 1.0))})))

(add-examples fields-list
  (example "List of all vector field names." (sort fields-list)))

(add-examples fields-list-random
  (example "List of all vector fields which give random results." (sort fields-list-random)))

(add-examples fields-list-not-random
  (example "List of all vector fields which are not random." (sort fields-list-not-random)))

;;

(add-examples combine
  (example "Create random combination" (let [f (combine)]
                                         (f (v/vec2 -0.5 0.5))))
  (example "Create combination for given configuration"
    (let [conf {:type :operation, :name :comp, :var1 {:type :variation, :name :blocky, :amount 1.0, :config {:x -1.4, :y 0.9, :mp 2.6}}, :var2 {:type :variation, :name :secant, :amount 1.0, :config {}}, :amount 1.0}
          f (combine conf)]
      (f (v/vec2 -0.5 0.5)))))

(add-examples random-configuration
  (example "Generate random configuration" (random-configuration))
  (example "One node configuration" (random-configuration 0))
  (example "Configuration with depth 2" (random-configuration 2)))

(add-examples randomize-configuration
  (example "Usage" (let [conf {:type :variation, :name :blocky, :amount 1.0, :config {:x -1.4, :y 0.9, :mp 2.6}}]
                     [(randomize-configuration conf)
                      (randomize-configuration conf)])))


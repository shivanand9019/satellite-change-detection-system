from unittest import result

from fastapi import FastAPI
from pydantic import BaseModel
from typing import List

app = FastAPI()

class DeltaInput(BaseModel):
    delta_array: List[float]

@app.post("/classify")
def classify(data: DeltaInput):

    result = []

    for value in data.delta_array:

        if value > 0.15:
            result.append("crop_growth")

        elif value < -0.15:
            result.append("crop_stress")

        else:
            result.append("no_change")
    print(data.delta_array)
    print(result)
    return {
        "classification": result
    }



#coding: utf-8
import face_recognition
from flask import Flask, jsonify, request, redirect, abort, Response
from flask_cors import CORS

from PIL import Image
import base64
import json
from io import BytesIO
import re
#import numpy as np
import tensorflow as tf
import time
import hmac
import logging

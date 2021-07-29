{{/*
Expand the name of the chart.
*/}}
{{- define "orange-talents-05-template-pix-keymanager-rest.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "orange-talents-05-template-pix-keymanager-rest.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "orange-talents-05-template-pix-keymanager-rest.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "orange-talents-05-template-pix-keymanager-rest.labels" -}}
helm.sh/chart: {{ include "orange-talents-05-template-pix-keymanager-rest.chart" . }}
{{ include "orange-talents-05-template-pix-keymanager-rest.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "orange-talents-05-template-pix-keymanager-rest.selectorLabels" -}}
app.kubernetes.io/name: {{ include "orange-talents-05-template-pix-keymanager-rest.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "orange-talents-05-template-pix-keymanager-rest.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "orange-talents-05-template-pix-keymanager-rest.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}
